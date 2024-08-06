package com.fiap.lanchonete.domain.entity.event;


import com.fiap.lanchonete.domain.entity.Pedido;

public class PedidoRealizadoEvent {
	Pedido pedidoRealizado;

	public Pedido getPedidoRealizado() {
		return pedidoRealizado;
	}

	public void setPedidoRealizado(Pedido pedidoRealizado) {
		this.pedidoRealizado = pedidoRealizado;
	}
	PedidoRealizadoEvent(){
		
	}
	public PedidoRealizadoEvent(Pedido pedidoRealizado){
		this.pedidoRealizado =pedidoRealizado;
	}
}
