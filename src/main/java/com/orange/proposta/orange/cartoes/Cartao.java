package com.orange.proposta.orange.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.orange.proposta.orange.bloqueio.Bloqueio;
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

	@OneToOne(cascade = CascadeType.MERGE)
	private Bloqueio bloqueio;

	@Enumerated(value = EnumType.STRING)
	private StatusCartao status = StatusCartao.ATIVO;

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

	public StatusCartao getStatus() {
		return status;
	}

	public void setStatus(StatusCartao status) {
		this.status = status;
	}

	public boolean bloqueado() {
		return this.status.equals(StatusCartao.BLOQUEADO);
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
}
