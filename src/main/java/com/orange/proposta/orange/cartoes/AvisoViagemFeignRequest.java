package com.orange.proposta.orange.cartoes;

import java.time.LocalDate;

import com.orange.proposta.orange.viagem.AvisoViagem;

public class AvisoViagemFeignRequest {

	private String destino;
	private LocalDate validoAte;

	public AvisoViagemFeignRequest(AvisoViagem avisoViagem) {
		this.destino = avisoViagem.getDestino();
		this.validoAte = avisoViagem.getTerminoViagem();
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

}
