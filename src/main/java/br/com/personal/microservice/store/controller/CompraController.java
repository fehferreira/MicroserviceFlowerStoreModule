package br.com.personal.microservice.store.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.personal.microservice.store.controller.dto.CompraDTO;

@RestController
@RequestMapping("compra")
public class CompraController {

	@RequestMapping(method = RequestMethod.POST)
	public void realizaCompra(@RequestBody CompraDTO compra) {
		System.out.println(compra);
	}
	
}
