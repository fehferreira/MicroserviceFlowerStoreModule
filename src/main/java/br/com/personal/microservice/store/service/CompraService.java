package br.com.personal.microservice.store.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.personal.microservice.store.client.FornecedorClient;
import br.com.personal.microservice.store.controller.dto.CompraDTO;
import br.com.personal.microservice.store.controller.dto.InfoFornecedorDTO;
import br.com.personal.microservice.store.controller.dto.InfoPedidoDTO;
import br.com.personal.microservice.store.model.Compra;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	@HystrixCommand
	public Compra realizaCompra(CompraDTO compra) {
		
		final String estado = compra.getEndereco().getEstado();
	
		LOG.info("Buscando informações de um fornecedor {}", estado);
		InfoFornecedorDTO fornecedorDTO = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		LOG.info("Realizando um pedido");
		InfoPedidoDTO pedidoDTO = fornecedorClient.realizaPedido(compra.getItens());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedidoDTO.getId());
		compraSalva.setTempoDePreparo(pedidoDTO.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;
	}

}
