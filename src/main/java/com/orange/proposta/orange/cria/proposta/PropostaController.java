package com.orange.proposta.orange.cria.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request) {

		Proposta proposta = request.toModel();

		propostaRepository.save(proposta);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId())
				.toUri();

		return ResponseEntity.created(uri).body(uri.toString());
	}

}
