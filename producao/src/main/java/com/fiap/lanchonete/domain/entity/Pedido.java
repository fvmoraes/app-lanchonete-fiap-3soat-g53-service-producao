package com.fiap.lanchonete.domain.entity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public class Pedido implements Comparable<Pedido>{
	
	Integer id;
	List<Produto> listaProdutos;
	StatusPedido statusPedido;
	BigDecimal valorTotal;
	LocalTime dataCricao;
	
	public Pedido(){
		
	}
	public Pedido(Integer id, List<Produto> string,
			StatusPedido statusPedido,  BigDecimal valorTotal, LocalTime dataCricao) {
	
		this.id = id;
		this.listaProdutos = string;
		this.valorTotal = valorTotal;
		this.dataCricao = dataCricao;
		this.statusPedido = statusPedido;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalTime getDataCricao() {
		return dataCricao;
	}
	public void setDataCricao(LocalTime dataCricao) {
		this.dataCricao = dataCricao;
	}

    @Override
    public int compareTo(Pedido outroPedido) {
        return this.dataCricao.compareTo(outroPedido.dataCricao);
    }
}
