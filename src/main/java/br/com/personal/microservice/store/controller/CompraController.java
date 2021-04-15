package br.com.personal.microservice.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.personal.microservice.store.controller.dto.CompraDTO;
import br.com.personal.microservice.store.model.Compra;
import br.com.personal.microservice.store.service.CompraService;

@RestController
@RequestMapping("compra")
public class CompraController {

	@Autowired
	private CompraService compraService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Compra realizaCompra(@RequestBody CompraDTO compra) {
		return compraService.realizaCompra(compra);
	}
	
	@RequestMapping("/{id}")
	public Compra getByID(@PathVariable Long id) {
		return compraService.getById(id);
	}
	
}
