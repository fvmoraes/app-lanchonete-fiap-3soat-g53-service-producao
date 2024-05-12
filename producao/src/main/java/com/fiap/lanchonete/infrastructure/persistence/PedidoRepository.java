package com.fiap.lanchonete.infrastructure.persistence;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;


public interface PedidoRepository  extends CrudRepository<PedidoEntity, Integer>{
    
	@AllowFiltering
    List<PedidoEntity> findAllByStatusPedidoAndDataCricaoBetween(StatusPedido status, LocalTime dataInicial, LocalTime dataFinal);	
}
