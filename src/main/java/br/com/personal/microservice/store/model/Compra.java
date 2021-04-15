package br.com.personal.microservice.store.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Compra {

	@Id
	private Long pedidoId;
	private Long voucher;
	private Integer tempoDePreparo;
	private String enderecoDestino;
	private LocalDate dataParaEntrega;
	
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Integer getTempoDePreparo() {
		return tempoDePreparo;
	}
	public void setTempoDePreparo(Integer tempoDePreparo) {
		this.tempoDePreparo = tempoDePreparo;
	}
	public String getEnderecoDestino() {
		return enderecoDestino;
	}
	public void setEnderecoDestino(String enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}
	
	public void setDataParaEntrega(LocalDate previsaoParaEntrega) {
		this.dataParaEntrega = previsaoParaEntrega;
	}
	
	public LocalDate getDataParaEntrega() {
		return dataParaEntrega;
	}
	
	public void setVoucher(Long numero) {
		this.voucher = numero;
	}
	
	public Long getVoucher() {
		return voucher;
	}
}
