package com.orange.proposta.orange.cartoes;

import com.orange.proposta.orange.carteira.Carteira;

public class CarteiraFeignRequest {

	private String email;

	private String carteira;

	public CarteiraFeignRequest(Carteira carteira) {
		this.email = carteira.getEmail();
		this.carteira = carteira.getTipoCarteira().toString();
	}

	public String getCarteira() {
		return carteira;
	}

	public String getEmail() {
		return email;
	}

}
