package com.orange.proposta.orange.cartoes;

public class BloqueioResponse {

	private StatusCartao resultado;

	public BloqueioResponse() {
	}

	public StatusCartao getResultado() {
		return resultado;
	}

	public void setResultado(StatusCartao resultado) {
		this.resultado = resultado;
	}

	public boolean bloqueado() {
		return this.resultado.equals(StatusCartao.BLOQUEADO);
	}
}
