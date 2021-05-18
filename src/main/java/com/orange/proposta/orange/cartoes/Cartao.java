package com.orange.proposta.orange.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.orange.proposta.orange.cria.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numeroCartao;

	private BigDecimal limite;

	@OneToOne(mappedBy = "cartao")
	private Proposta proposta;

	private LocalDateTime emitidoEm = LocalDateTime.now();

	@Deprecated
	public Cartao() {

	}

	public Cartao(String numeroCartao, BigDecimal limite, Proposta proposta) {
		super();
		this.numeroCartao = numeroCartao;
		this.limite = limite;
		this.proposta = proposta;
	}

}
