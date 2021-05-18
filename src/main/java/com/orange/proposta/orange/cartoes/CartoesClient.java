package com.orange.proposta.orange.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "${cartoes.host}")
public interface CartoesClient {

	@GetMapping
	NovoCartaoResponse findPropostaId(@RequestParam(name = "idProposta") Long idProposta);

}
