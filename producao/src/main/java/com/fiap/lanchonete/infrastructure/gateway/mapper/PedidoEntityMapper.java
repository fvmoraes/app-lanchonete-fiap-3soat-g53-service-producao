package com.fiap.lanchonete.infrastructure.gateway.mapper;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoEntityMapper {

	public PedidoEntity paraPedidoEntity(Pedido PedidoObjectDomain) throws JsonProcessingException {
		ObjectMapper mappr = new ObjectMapper();
		return new PedidoEntity(PedidoObjectDomain.getId(),
				mappr.writeValueAsString(PedidoObjectDomain.getListaProdutos()), PedidoObjectDomain.getStatusPedido(),
				PedidoObjectDomain.getValorTotal());

	}

	public Pedido paraObjetoDominio(PedidoEntity pedidoEntity) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Produto> produtos = objectMapper.readValue(pedidoEntity.getListaProdutosPedido(),
				new TypeReference<List<Produto>>() {
				});

		return new Pedido(pedidoEntity.getId(), produtos, pedidoEntity.getStatusPedido(), pedidoEntity.getValorTotal(),
				pedidoEntity.getDataCricao());
	}
}