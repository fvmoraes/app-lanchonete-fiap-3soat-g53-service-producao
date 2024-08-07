package com.fiap.lanchonete.application.gateways;

import java.util.List;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public interface PedidoGateway {
	Pedido criaPedido(Pedido pedido);

	void atualizaPedido(Pedido pedido);

	List<Pedido> buscaPedidos();

	Pedido buscaPedidoId(Integer id);

	List<Pedido> buscaPedidosStatus(StatusPedido status); 

}
