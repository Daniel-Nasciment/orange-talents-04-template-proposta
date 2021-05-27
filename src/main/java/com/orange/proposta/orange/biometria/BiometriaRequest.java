package com.orange.proposta.orange.biometria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.orange.proposta.orange.cartoes.Cartao;

public class BiometriaRequest {

	@NotNull
	@NotEmpty
	private byte[] impressaoDigital;

	public void setImpressaoDigital(byte[] impressaoDigital) {
		this.impressaoDigital = impressaoDigital;
	}

	public Biometria toModel(Cartao cartao) {

		return new Biometria(this.impressaoDigital, cartao);
	}

}
