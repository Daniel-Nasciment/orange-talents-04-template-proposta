package com.orange.proposta.orange.biometria;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orange.proposta.orange.cartoes.Cartao;
import com.orange.proposta.orange.cartoes.CartaoRepository;

@RestController
@RequestMapping("/biometrias/{id}")
public class BiometriaController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BiometriaRepository biometriaRepository;

	@PostMapping
	public ResponseEntity<?> salvaBiometria(@RequestBody @Valid BiometriaRequest request, @PathVariable Long id) {

		Cartao cartao = cartaoRepository.findById(id).get();
		
		Biometria biometria = request.toModel(cartao);

		biometriaRepository.save(biometria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(biometria.getId())
				.toUri();

		return ResponseEntity.created(uri).body(uri.toString());
	}

}
