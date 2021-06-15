package com.orange.proposta.orange.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orange.proposta.orange.cartoes.Cartao;

public class CarteiraRequest {

	@Email
	@NotBlank
	private String email;

	@NotNull
	private TipoCarteira tipoCarteira;

	public CarteiraRequest(@Email @NotBlank String email, @NotNull TipoCarteira tipoCarteira) {
		this.email = email;
		this.tipoCarteira = tipoCarteira;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getTipoCarteira() {
		return tipoCarteira;
	}

	public Carteira toModel(Cartao cartao) {
		return new Carteira(this.email, this.tipoCarteira, cartao);
	}

}
