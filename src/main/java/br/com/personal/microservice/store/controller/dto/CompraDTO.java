package br.com.personal.microservice.store.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompraDTO {

	@JsonIgnore
	private Long compraId;
	private List<ItemCompraDTO> itens;
	private EnderecoDTO endereco;
	
	public List<ItemCompraDTO> getItens() {
		return itens;
	}
	public void setItens(List<ItemCompraDTO> itens) {
		this.itens = itens;
	}
	public EnderecoDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public Long getCompraId() {
		return compraId;
	}
	public void setCompraId(Long compraId) {
		this.compraId = compraId;
	}	

	@Override
	public String toString() {
		return "CompraDTO [itens=" + itens + ", endereco=" + endereco + "]";
	}
}
