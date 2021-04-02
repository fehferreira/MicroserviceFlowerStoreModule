package br.com.personal.microservice.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.personal.microservice.store.controller.dto.CompraDTO;
import br.com.personal.microservice.store.service.CompraService;

@RestController
@RequestMapping("compra")
public class CompraController {

	@Autowired
	private CompraService compraService;
	
	@RequestMapping(method = RequestMethod.POST)
	public void realizaCompra(@RequestBody CompraDTO compra) {
		compraService.realizaCompra(compra);
	}
	
}
