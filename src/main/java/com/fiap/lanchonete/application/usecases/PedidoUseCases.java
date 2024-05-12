package com.fiap.lanchonete.application.usecases;

import java.util.List;

import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public interface PedidoUseCases {

	public List<Pedido> buscaPedidos();

	public List<Pedido> buscaPedidosPorStatus(StatusPedido status);

	public Pedido buscaPedidoId(Integer id) throws PedidoNaoEncontradoException;
	
	public Pedido buscaProximoPedido();
	
	public Pedido atualizaPedidoStatus(Integer id, StatusPedido status);
	
	public Pedido criaPedido(Pedido pedido);
}
