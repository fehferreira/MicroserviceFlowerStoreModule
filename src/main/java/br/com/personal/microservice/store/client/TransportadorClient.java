package br.com.personal.microservice.store.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.personal.microservice.store.controller.dto.InfoEntregaDTO;
import br.com.personal.microservice.store.controller.dto.VoucherDTO;

@FeignClient("transportador")
public interface TransportadorClient {
	
	@RequestMapping(path = "/entrega", method = RequestMethod.POST)
	VoucherDTO reservaEntrega(@RequestBody InfoEntregaDTO pedidoDTO);

}
