package com.orange.proposta.orange.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.orange.proposta.orange.cria.proposta.Proposta;
import com.orange.proposta.orange.cria.proposta.PropostaRepository;

public class NovoCartaoResponse {

	private String id;

	private BigDecimal limite;

	private LocalDateTime emitidoEm = LocalDateTime.now();

	private Long idProposta;

	public String getId() {
		return id;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public Cartao toModel(PropostaRepository propostaRepository) {

		Proposta proposta = propostaRepository.findById(idProposta).get();

		return new Cartao(this.id, this.limite, proposta);
	}

}
