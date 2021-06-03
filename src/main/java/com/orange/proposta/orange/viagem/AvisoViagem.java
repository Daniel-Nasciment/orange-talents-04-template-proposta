package com.orange.proposta.orange.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orange.proposta.orange.cartoes.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String destino;

	@NotNull
	@Future
	private LocalDate terminoViagem;

	private LocalDateTime instanteAvisoViagem = LocalDateTime.now();

	private String ipCliente;

	private String userAgent;

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public AvisoViagem() {

	}

	public AvisoViagem(@NotBlank String destino, @NotNull @Future LocalDate terminoViagem, String ipCliente,
			String userAgent, Cartao cartao) {
		super();
		this.destino = destino;
		this.terminoViagem = terminoViagem;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getTerminoViagem() {
		return terminoViagem;
	}

	public LocalDateTime getInstanteAvisoViagem() {
		return instanteAvisoViagem;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
