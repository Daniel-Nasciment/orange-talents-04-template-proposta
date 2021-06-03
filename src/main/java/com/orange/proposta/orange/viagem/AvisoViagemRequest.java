package com.orange.proposta.orange.viagem;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orange.proposta.orange.cartoes.Cartao;

public class AvisoViagemRequest {

	@NotBlank
	private String destino;

	@NotNull
	@Future
	private LocalDate terminoViagem;

	public AvisoViagemRequest(@NotBlank String destino, @NotNull @Future LocalDate terminoViagem) {
		super();
		this.destino = destino;
		this.terminoViagem = terminoViagem;
	}

	public AvisoViagem toModel(HttpServletRequest servletRequest, String userAgent, Cartao possivelCartao) {
		return new AvisoViagem(this.destino, this.terminoViagem, servletRequest.getRemoteAddr(), userAgent,
				possivelCartao);
	}

}
