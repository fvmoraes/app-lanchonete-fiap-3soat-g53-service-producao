package com.fiap.lanchonete.infrastructure.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.domain.entity.event.PedidoAtualizadoEvent;
import com.fiap.lanchonete.infrastructure.mapper.PedidoRequestMapper;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1/producao/pedido")
public class PedidoController {
	
	private static final String PEDIDO_EXCHANGE_1 = "pedido-exchange";
	private static final String PRODUCAO_PEDIDO_ROUTING_KEY = "producao-para-pedido-routing-key";

	private final PedidoUseCases pedidoUseCases;
	private final PedidoRequestMapper mapper;
	private final RabbitTemplate template;

	public PedidoController(PedidoUseCases pedidoUseCases, PedidoRequestMapper mapper, RabbitTemplate template) {
		this.pedidoUseCases = pedidoUseCases;
		this.mapper = mapper;
		this.template = template;
	}

	@GetMapping
	@Operation(description = "Busca Pedidos")
	public List<PedidoResponse> buscaPedidos() {
		return pedidoUseCases.buscaPedidos().stream().map(mapper::paraResponse).toList();
	}
	
	@PostMapping
	@Hidden
	@Operation(description = "Cria um Pedido, usado apenas para testes")
	public PedidoResponse criaPedidos(@RequestBody Pedido pedido) {
		return mapper.paraResponse(pedidoUseCases.criaPedido(pedido));
	}

	@GetMapping("proximo")
	@Operation(description = "Busca próximo pedido a ser feito")
	public ResponseEntity<PedidoResponse> buscaPoximoPedido() {
			return new ResponseEntity<>(mapper.paraResponse(pedidoUseCases.buscaProximoPedido()), HttpStatus.OK);	
	}
	
	@GetMapping("{id}")
	@Operation(description = "Busca pedido por ID")
	public ResponseEntity<PedidoResponse> buscaPedidosPorId(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(mapper.paraResponse(pedidoUseCases.buscaPedidoId(id)), HttpStatus.OK);
		} catch (PedidoNaoEncontradoException e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	};

	@GetMapping("/status/{status}")
	@Operation(description = "Busca pedido Status")
	public List<PedidoResponse> buscaPedidosPorStatus(@PathVariable StatusPedido status) {
		return pedidoUseCases.buscaPedidosPorStatus(status).stream().map(mapper::paraResponse).toList();
	};

	@PostMapping("{id}/{status}")
	@Operation(description = "Atualiza o Pedido do ID pelo Status fornecido")
	public PedidoResponse atualizaPedidosStatus(@PathVariable("status") StatusPedido status, @PathVariable("id") int id) {
		 Pedido pedido = pedidoUseCases.atualizaPedidoStatus(id,status);
		 PedidoAtualizadoEvent event = new PedidoAtualizadoEvent(id,status);
		 template.convertAndSend(PEDIDO_EXCHANGE_1, PRODUCAO_PEDIDO_ROUTING_KEY, event);
		 return mapper.paraResponse(pedido);
	};
	
}
