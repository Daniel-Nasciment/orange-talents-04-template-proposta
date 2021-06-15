package com.orange.proposta.orange.cartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "${cartoes.host}")
public interface CartoesClient {

	@GetMapping
	NovoCartaoResponse findPropostaId(@RequestParam(name = "idProposta") Long idProposta);

	@PostMapping(value = "{id}/bloqueios")
	public BloqueioResponse bloquearCartao(@PathVariable("id") String idCartao,
			@RequestBody BloqueioRequest request);

	@PostMapping(value = "/{id}/avisos")
	public void avisoViagem(@PathVariable("id") String idCartao, @RequestBody AvisoViagemFeignRequest request);

	@PostMapping(value = "/{id}/carteiras")
	public CarteiraFeignResponse associaCarteira(@PathVariable("id") String idCartao, @RequestBody CarteiraFeignRequest request);
}
