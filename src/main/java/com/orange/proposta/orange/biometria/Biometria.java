package com.orange.proposta.orange.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.orange.proposta.orange.cartoes.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Lob
	private byte[] fingerPrint;

	@ManyToOne
	private Cartao cartao;

	@NotNull
	private LocalDateTime instanteAssociacao = LocalDateTime.now();

	@Deprecated
	public Biometria() {

	}

	public Biometria(@NotNull @NotEmpty byte[] fingerPrint, Cartao cartao) {
		super();
		this.fingerPrint = fingerPrint;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

}
