package br.com.personal.microservice.store.controller.dto;

import java.util.List;

import br.com.personal.microservice.store.model.CompraState;

public class CompraDTO {

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
	
	@Override
	public String toString() {
		return "CompraDTO [itens=" + itens + ", endereco=" + endereco + "]";
	}	
}
