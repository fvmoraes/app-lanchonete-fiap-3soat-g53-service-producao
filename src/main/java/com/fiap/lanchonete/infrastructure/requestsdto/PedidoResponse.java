package com.fiap.lanchonete.infrastructure.requestsdto;

import java.math.BigDecimal;
import java.util.List;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoResponse {

	private Integer idPedido;

	private List<Produto> listaProdutos;
	private StatusPedido statusPedido;
	private BigDecimal valorTotal;
	
	public PedidoResponse(Integer idPedido, List<Produto> listaProdutos, 
			StatusPedido statusPedido, BigDecimal valorTotal) {
		super();
		this.idPedido = idPedido;
		this.listaProdutos = listaProdutos;
		this.valorTotal = valorTotal;
		this.statusPedido = statusPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}


}
