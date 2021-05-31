package com.orange.proposta.orange.bloqueio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.proposta.orange.cartoes.BloqueioRequest;
import com.orange.proposta.orange.cartoes.BloqueioResponse;
import com.orange.proposta.orange.cartoes.Cartao;
import com.orange.proposta.orange.cartoes.CartaoRepository;
import com.orange.proposta.orange.cartoes.CartoesClient;

import feign.FeignException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BloqueioRepository bloqueioRepository;

	@Autowired
	private CartoesClient cartoesClient;

	@PostMapping(value = "/{idCartao}")
	public ResponseEntity<?> bloqueiaCartao(@PathVariable("idCartao") Long idCartao, HttpServletRequest servletRequest,
			@RequestHeader(value = "User-Agent") String userAgent) {

		Cartao possivelCartao = cartaoRepository.findById(idCartao).get();

		Bloqueio novoBloqueio = new Bloqueio(servletRequest.getRemoteAddr(), userAgent, possivelCartao);
		BloqueioResponse bloqueioFeignResponse = cartoesClient.bloquearCartao(possivelCartao.getNumeroCartao(),
				new BloqueioRequest());
		try {
			bloqueioRepository.save(novoBloqueio);
			return ResponseEntity.ok(bloqueioFeignResponse.getResultado().toString());

		} catch (FeignException.UnprocessableEntity e) {
			e.printStackTrace();
		}

		return ResponseEntity.unprocessableEntity().build();
	}

}
