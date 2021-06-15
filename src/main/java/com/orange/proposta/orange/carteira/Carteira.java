package com.orange.proposta.orange.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orange.proposta.orange.cartoes.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String email;

	@Enumerated(EnumType.STRING)
	private TipoCarteira tipoCarteira;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Carteira() {

	}

	public Carteira(String email, TipoCarteira tipoCarteira, Cartao cartao) {
		this.email = email;
		this.tipoCarteira = tipoCarteira;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getTipoCarteira() {
		return tipoCarteira;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
