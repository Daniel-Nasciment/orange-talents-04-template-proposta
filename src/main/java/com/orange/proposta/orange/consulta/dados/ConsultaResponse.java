package com.orange.proposta.orange.consulta.dados;

//Classe baseada no serviço externo
public class ConsultaResponse {

	private String documento;

	private String nome;

	private ResultadoSolicitacao resultadoSolicitacao;

	private Long idProposta;

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public Long getIdProposta() {
		return idProposta;
	}

}
