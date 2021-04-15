package br.com.personal.microservice.store.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.personal.microservice.store.client.FornecedorClient;
import br.com.personal.microservice.store.client.TransportadorClient;
import br.com.personal.microservice.store.controller.dto.CompraDTO;
import br.com.personal.microservice.store.controller.dto.InfoEntregaDTO;
import br.com.personal.microservice.store.controller.dto.InfoFornecedorDTO;
import br.com.personal.microservice.store.controller.dto.InfoPedidoDTO;
import br.com.personal.microservice.store.controller.dto.VoucherDTO;
import br.com.personal.microservice.store.model.Compra;
import br.com.personal.microservice.store.model.CompraState;
import br.com.personal.microservice.store.repository.CompraRepository;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	@Autowired
	private TransportadorClient transportadorClient;
	
	@HystrixCommand(threadPoolKey = "getByIdThreadPool")
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());		
	}
	
	@HystrixCommand(fallbackMethod = "realizaCompraFallback",
			threadPoolKey = "realizaCompraThreadPool")
	public Compra realizaCompra(CompraDTO compra) {
		
		Compra compraSalva = new Compra();
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		compraSalva.setState(CompraState.RECEBIDO);
		compraRepository.save(compraSalva);
	
		InfoFornecedorDTO fornecedorDTO = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		InfoPedidoDTO pedidoDTO = fornecedorClient.realizaPedido(compra.getItens());
		compraSalva.setPedidoId(pedidoDTO.getId());
		compraSalva.setTempoDePreparo(pedidoDTO.getTempoDePreparo());
		compraSalva.setState(CompraState.PEDIDO_REALIZADO);
		compraRepository.save(compraSalva);
		
		InfoEntregaDTO entregaDTO = new InfoEntregaDTO();
		entregaDTO.setPedidoId(pedidoDTO.getId());
		entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(pedidoDTO.getTempoDePreparo()));
		entregaDTO.setEnderecoOrigem(fornecedorDTO.getEndereco());
		entregaDTO.setEnderecoDestino(compra.getEndereco().toString());
		VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);
		compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compraSalva.setVoucher(voucher.getNumero());
		compraSalva.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
		compraRepository.save(compraSalva);		
		
		return compraSalva;
	}
	
	public Compra realizaCompraFallback(CompraDTO compra) {
		Compra compraFallBack = new Compra();
		compraFallBack.setEnderecoDestino(compra.getEndereco().toString());
		return compraFallBack;
	}
}
