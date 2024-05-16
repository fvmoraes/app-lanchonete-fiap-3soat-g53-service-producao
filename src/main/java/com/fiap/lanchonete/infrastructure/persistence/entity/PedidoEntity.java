package com.fiap.lanchonete.infrastructure.persistence.entity;



import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPedido;

import jakarta.validation.constraints.NotNull;

@Lazy
@Table
public class PedidoEntity {

	@Id
	private Integer id;

    private String listaProdutosPedido;
	 
	@NotNull
	private StatusPedido statusPedido;
	
	private BigDecimal valorTotal;
	
	private LocalTime dataCricao = LocalTime.now();
	
	
	public PedidoEntity() {
		
	}

	public PedidoEntity(String listaProdutosPedido, 
			@NotNull StatusPedido statusPedido, BigDecimal valorTotal) {
		this.listaProdutosPedido = listaProdutosPedido;
		this.statusPedido = statusPedido;
		this.valorTotal = valorTotal;

	}
	public PedidoEntity(Integer id,String list,
			@NotNull StatusPedido statusPedido, BigDecimal valorTotal) {
		this.id = id;
		this.listaProdutosPedido = list;
		this.statusPedido = statusPedido;
		this.valorTotal = valorTotal;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getListaProdutosPedido() {
		return listaProdutosPedido;
	}

	public void setListaProdutosPedido(String listaProdutosPedido) {
		this.listaProdutosPedido = listaProdutosPedido;
	}

	

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public void setDataCricao(LocalTime dataCricao) {
		this.dataCricao = dataCricao;
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
		
}
