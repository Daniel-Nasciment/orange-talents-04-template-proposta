package com.orange.proposta.orange.viagem;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.aspectj.lang.reflect.NoSuchAdviceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.proposta.orange.cartoes.AvisoViagemFeignRequest;
import com.orange.proposta.orange.cartoes.Cartao;
import com.orange.proposta.orange.cartoes.CartaoRepository;
import com.orange.proposta.orange.cartoes.CartoesClient;
import com.orange.proposta.orange.exception.ExceptionHandler;

@RestController
@RequestMapping(value = "/avisos")
public class AvisoViagemController {

	@Autowired
	private CartaoRepository cartaoReposiory;

	@Autowired
	private AvisoViagemRepository avisoViagemRepository;

	@Autowired
	private CartoesClient cartoesClient;

	@PostMapping(value = "/{id}")
	public ResponseEntity<?> criaAvisoViagem(@PathVariable("id") Long idCartao,
			@RequestBody @Valid AvisoViagemRequest request, HttpServletRequest servletRequest,
			@RequestHeader(value = "User-Agent") String userAgent) {

		Optional<Cartao> possivelCartao = cartaoReposiory.findById(idCartao);

		if (possivelCartao.get().bloqueado() || !possivelCartao.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		AvisoViagem avisoViagem = request.toModel(servletRequest, userAgent, possivelCartao.get());

		cartoesClient.avisoViagem(possivelCartao.get().getNumeroCartao(), new AvisoViagemFeignRequest(avisoViagem));

		avisoViagemRepository.save(avisoViagem);

		return ResponseEntity.ok(new AvisoViagemResponse());
	}

}
