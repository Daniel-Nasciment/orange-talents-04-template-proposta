package com.orange.proposta.orange.consulta.dados;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Essa chave está alocada no meu application.properties
//Geralmente quando trabalhamos com aplicação Cliente devemos ter o sufixo @Feign
//
@FeignClient(value = "analises", url = "${analises.host}")
public interface ConsultaDadosProposta {

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ConsultaResponse analisaDados(ConsultaRequest request);

}
