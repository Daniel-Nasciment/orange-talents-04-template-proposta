package com.orange.proposta.orange.cria.proposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.orange.proposta.orange.cartoes.Cartao;
import com.orange.proposta.orange.consulta.dados.StatusAnalisado;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String documento;

	@NotBlank
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@Positive
	private BigDecimal salario;

	// Segundo o que pede a FEATURE o que dever ser salvo é o conteudo desse ENUM
	@Enumerated(EnumType.STRING)
	private StatusAnalisado status;

	@OneToOne
	private Cartao cartao;

	@Deprecated
	public Proposta() {

	}

	public Proposta(@NotBlank String documento, @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public StatusAnalisado getStatus() {
		return status;
	}

	public Cartao getCartao() {
		return cartao;
	}

	// O SET É PASSADO DENTRO DO MEU CONTROLLER

	public void setStatus(StatusAnalisado status) {
		this.status = status;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

}
