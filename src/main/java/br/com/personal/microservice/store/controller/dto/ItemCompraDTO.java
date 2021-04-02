package br.com.personal.microservice.store.controller.dto;

public class ItemCompraDTO {

	private Long id;
	private Integer quantidade;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public String toString() {
		return "ItemCompraDTO [id=" + id + ", quantidade=" + quantidade + "]";
	}

}
