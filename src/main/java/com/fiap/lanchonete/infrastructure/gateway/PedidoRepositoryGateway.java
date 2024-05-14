package com.fiap.lanchonete.infrastructure.gateway;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoRepositoryGateway implements PedidoGateway {

	private final PedidoRepository repository;
	private final PedidoEntityMapper mapper;

	public PedidoRepositoryGateway(PedidoRepository repository, PedidoEntityMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Pedido criaPedido(Pedido pedido) {
		try {
			return mapper.paraObjetoDominio(repository.save(mapper.paraPedidoEntity(pedido)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void atualizaPedido(Pedido pedido) {
		try {
			repository.save(mapper.paraPedidoEntity(pedido));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pedido> buscaPedidos() {
		 Iterable<PedidoEntity> pedidos = repository.findAll();
		    
		    List<Pedido> listaPedidos = new ArrayList<Pedido>();
		    for (PedidoEntity pedidoEntity : pedidos) {
		        try {
		            Pedido pedido = mapper.paraObjetoDominio(pedidoEntity);
		            listaPedidos.add(pedido);
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		        }
		    }
		    Collections.sort(listaPedidos);
		    return listaPedidos;
	}
	
	
	@Override
	public Pedido buscaPedidoId(Integer id) {
		Optional<PedidoEntity> pedidos = repository.findById(id);
		if (pedidos.isPresent())
			try {
				return mapper.paraObjetoDominio(pedidos.get());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<Pedido> buscaPedidosStatus(StatusPedido status) {
		 Iterable<PedidoEntity> pedidos = repository.findAll();
		    
		    List<Pedido> listaPedidos = new ArrayList<Pedido>();
		    for (PedidoEntity pedidoEntity : pedidos) {
		        try {
		            Pedido pedido = mapper.paraObjetoDominio(pedidoEntity);
		            if (pedido.getStatusPedido().equals(status))
		            	listaPedidos.add(pedido);
		           
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		        }
		    }
		    Collections.sort(listaPedidos);
		    return listaPedidos;
	}
}