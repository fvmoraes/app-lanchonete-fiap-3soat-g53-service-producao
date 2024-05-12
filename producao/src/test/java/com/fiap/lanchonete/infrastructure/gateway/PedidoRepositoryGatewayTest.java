package com.fiap.lanchonete.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

@SpringBootTest
public class PedidoRepositoryGatewayTest {

    @Mock
    private PedidoRepository repository;

    @Mock
    private PedidoEntityMapper mapper;

    @InjectMocks
    private PedidoRepositoryGateway gateway;

    @Test
    void criaPedido() throws JsonProcessingException {
        Pedido pedido = mock(Pedido.class);
        PedidoEntity pedidoEntity = mock(PedidoEntity.class);
        when(mapper.paraPedidoEntity(pedido)).thenReturn(pedidoEntity);
        when(repository.save(pedidoEntity)).thenReturn(pedidoEntity);
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        assertEquals(pedido, gateway.criaPedido(pedido));
    }

    @Test
    void atualizaPedido() throws JsonProcessingException {
        Pedido pedido = mock(Pedido.class);
        PedidoEntity pedidoEntity = mock(PedidoEntity.class);
        when(mapper.paraPedidoEntity(pedido)).thenReturn(pedidoEntity);
        gateway.atualizaPedido(pedido);

        verify(repository).save(pedidoEntity);
    }

    @Test
    void buscaPedidos() throws JsonProcessingException {
        List<PedidoEntity> pedidoEntities = new ArrayList<>();
        PedidoEntity pedidoEntity = mock(PedidoEntity.class);
        pedidoEntities.add(pedidoEntity);
        Iterable<PedidoEntity> iterable = pedidoEntities;
        when(repository.findAll()).thenReturn(iterable);

        Pedido pedido = mock(Pedido.class);
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        List<Pedido> expectedPedidos = new ArrayList<>();
        expectedPedidos.add(pedido);

        assertEquals(expectedPedidos, gateway.buscaPedidos());
    }

    @Test
    void buscaPedidoId_Presente() throws JsonProcessingException {
        Integer id = 1;
        PedidoEntity pedidoEntity = mock(PedidoEntity.class);
        when(repository.findById(id)).thenReturn(Optional.of(pedidoEntity));

        Pedido pedido = mock(Pedido.class);
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        assertEquals(pedido, gateway.buscaPedidoId(id));
    }

    @Test
    void buscaPedidoId_Ausente() throws JsonProcessingException {
        Integer id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertEquals(null, gateway.buscaPedidoId(id));
    }

    @Test
    void buscaPedidosStatus() throws JsonProcessingException {
        StatusPedido status = StatusPedido.RECEBIDO;
        List<PedidoEntity> pedidoEntities = new ArrayList<>();
        PedidoEntity pedidoEntity = mock(PedidoEntity.class);
        pedidoEntities.add(pedidoEntity);
        Iterable<PedidoEntity> iterable = pedidoEntities;
        when(repository.findAll()).thenReturn(iterable);

        Pedido pedido = mock(Pedido.class);
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);
        when(pedido.getStatusPedido()).thenReturn(status);

        List<Pedido> expectedPedidos = new ArrayList<>();
        expectedPedidos.add(pedido);

        assertEquals(expectedPedidos, gateway.buscaPedidosStatus(status));
    }
}