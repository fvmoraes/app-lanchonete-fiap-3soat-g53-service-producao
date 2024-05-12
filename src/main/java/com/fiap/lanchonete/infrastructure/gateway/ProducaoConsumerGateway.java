package com.fiap.lanchonete.infrastructure.gateway;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.event.PedidoRealizadoEvent;

@Component
public class ProducaoConsumerGateway {
	private static final String PRODUCAO_QUEUE_1 = "producao-queue";

	final PedidoUseCases pedidoUseCases;
	
	ProducaoConsumerGateway(PedidoUseCases pedidoUseCases){
		this.pedidoUseCases = pedidoUseCases;
	}
	
	@RabbitListener(queues = PRODUCAO_QUEUE_1)
	public void pedidoRecebido(PedidoRealizadoEvent pedidoRealizado) throws PedidoNaoEncontradoException {
		pedidoUseCases.criaPedido(pedidoRealizado.getPedidoRealizado());
		System.out.println("Pedido Cadastrado com sucesso!");
	}
	
}
