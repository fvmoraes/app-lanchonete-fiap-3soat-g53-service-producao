package com.fiap.lanchonete.infrastructure.mapper;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

public class PedidoRequestMapper {
	

	public PedidoResponse paraResponse(Pedido pedido) {
		return new PedidoResponse(pedido.getId(), pedido.getListaProdutos(), pedido.getStatusPedido(), pedido.getValorTotal());
	}
}
