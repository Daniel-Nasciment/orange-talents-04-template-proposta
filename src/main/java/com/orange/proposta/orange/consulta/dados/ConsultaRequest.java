package com.orange.proposta.orange.consulta.dados;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Classe baseada no serviço externo
public class ConsultaRequest {

	@NotBlank
	private String documento;

	@NotBlank
	private String nome;

	@NotNull
	private Long idProposta;

	public ConsultaRequest() {
		
	}
	
	public ConsultaRequest(@NotBlank String documento, @NotBlank String nome, @NotNull Long idProposta) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}

}
