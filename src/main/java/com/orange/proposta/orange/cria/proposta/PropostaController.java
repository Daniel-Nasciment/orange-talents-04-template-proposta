package com.orange.proposta.orange.cria.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orange.proposta.orange.consulta.dados.ConsultaDadosProposta;
import com.orange.proposta.orange.consulta.dados.ConsultaRequest;
import com.orange.proposta.orange.consulta.dados.ConsultaResponse;
import com.orange.proposta.orange.consulta.dados.StatusAnalisado;

import feign.FeignException;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	// FAZER A INJEÇÃO DA DEPENDENCIA DO FEIGN 
	
	@Autowired
	private ConsultaDadosProposta consultaDadosProposta;

	@GetMapping(value = "/{id}")
	public NovaPropostaResponse buscaProposta(@PathVariable Long id) {

		Proposta proposta = propostaRepository.findById(id).get();

		return new NovaPropostaResponse(proposta);
	}

	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request) {

		Proposta proposta = request.toModel();

		if (request.localizaDocIgual(propostaRepository)) {
			return ResponseEntity.unprocessableEntity().body("Você já realizou uma proposta");
		}

		propostaRepository.save(proposta);

		// Preciso de uma proposta para montar uma solicitação de consulta
		// A partir da PROPOSTA salva eu preencho a ConsultaRequest baseado no que ela pede
		ConsultaRequest consultaRequest = new ConsultaRequest(proposta.getDocumento(), proposta.getNome(),
				proposta.getId());

		try {

		// Com a injeção do FEIGN eu chamo para utilizar o seu métódo "analisaDados" passando uma ConsultaReques 
			ConsultaResponse consultaResponse = consultaDadosProposta.analisaDados(consultaRequest);
		
		// Só entrara dentro do TRY se for SEM_RESTRICAO
			proposta.setStatus(StatusAnalisado.ELEGIVEL);

			propostaRepository.save(proposta);
		} catch (FeignException.UnprocessableEntity e) {

		// Só entra no catch se for COM_RESTRICAO
			
			System.out.println("Ocorreu um erro inesperado.");
			proposta.setStatus(StatusAnalisado.NAO_ELEGIVEL);
			propostaRepository.save(proposta);

			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId())
				.toUri();

		return ResponseEntity.created(uri).body(uri.toString());
	}

}
