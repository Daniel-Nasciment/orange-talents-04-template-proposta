package com.orange.proposta.orange.carteira;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.proposta.orange.cartoes.Cartao;
import com.orange.proposta.orange.cartoes.CartaoRepository;
import com.orange.proposta.orange.cartoes.CarteiraFeignRequest;
import com.orange.proposta.orange.cartoes.CartoesClient;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CartoesClient cartoesClient;

	@Autowired
	private CarteiraRepository carteiraRepository;

	@PostMapping("/{id}")
	public ResponseEntity<?> associaCarteira(@PathVariable("id") Long idCartao,
			@RequestBody @Valid CarteiraRequest request, UriComponentsBuilder builder) {

		Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);

		Carteira carteira = request.toModel(possivelCartao.get());

		if (carteiraRepository.existsByTipoCarteiraAndCartao(request.getTipoCarteira(), possivelCartao.get())) {
			return ResponseEntity.unprocessableEntity().build();
		}

		cartoesClient.associaCarteira(possivelCartao.get().getNumeroCartao(), new CarteiraFeignRequest(carteira));

		carteiraRepository.save(carteira);

		URI uri = builder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
