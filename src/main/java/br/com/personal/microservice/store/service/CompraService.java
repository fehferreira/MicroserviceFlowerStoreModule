package br.com.personal.microservice.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.personal.microservice.store.client.FornecedorClient;
import br.com.personal.microservice.store.controller.dto.CompraDTO;
import br.com.personal.microservice.store.controller.dto.InfoFornecedorDTO;
import br.com.personal.microservice.store.controller.dto.InfoPedidoDTO;
import br.com.personal.microservice.store.model.Compra;

@Service
public class CompraService {

	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(CompraDTO compra) {
	
		InfoFornecedorDTO fornecedorDTO = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		InfoPedidoDTO pedidoDTO = fornecedorClient.realizaPedido(compra.getItens());
		
		System.out.println(fornecedorDTO.getEndereco());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedidoDTO.getId());
		compraSalva.setTempoDePreparo(pedidoDTO.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;
	}

}
