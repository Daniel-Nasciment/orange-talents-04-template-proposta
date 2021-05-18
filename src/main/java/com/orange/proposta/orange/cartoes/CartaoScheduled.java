package com.orange.proposta.orange.cartoes;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.orange.proposta.orange.consulta.dados.StatusAnalisado;
import com.orange.proposta.orange.cria.proposta.Proposta;
import com.orange.proposta.orange.cria.proposta.PropostaRepository;

import feign.FeignException;

// Anotar como um component
@Component
public class CartaoScheduled {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private PropostaRepository propostaRepository;

	// Possuir o Client do serviço  injetado
	@Autowired
	private CartoesClient cartoesClient;

	@Scheduled(fixedDelay = 60 * 1000)
	public void doSomething() {

		// Busca todas propostas com status elegivel que não possuem cartão e armazeno em um SET de PROPOSTA
		Set<Proposta> propostas = propostaRepository.findByStatusWhereCartaoIsNull(StatusAnalisado.ELEGIVEL);

		for (Proposta proposta : propostas) {

			try {
				NovoCartaoResponse response = cartoesClient.findPropostaId(proposta.getId());
				Cartao cartao = response.toModel(propostaRepository);

				cartaoRepository.save(cartao);

				proposta.setCartao(cartao);
				
				propostaRepository.save(proposta);

			} catch (FeignException e) {

				// Me mostra o erro que está dando
				e.printStackTrace();

			}

		}

	}

}
