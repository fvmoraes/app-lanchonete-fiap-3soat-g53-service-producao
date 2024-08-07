package com.fiap.lanchonete.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoRepositoryGatewayTest {


    @Mock
    private PedidoRepository repository;

    @Mock
    private PedidoEntityMapper mapper;

    @InjectMocks
    private PedidoRepositoryGateway gateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriaPedido() throws JsonProcessingException {
        // Mocking
        Pedido pedido = new Pedido();
        PedidoEntity pedidoEntity = new PedidoEntity();
        when(mapper.paraPedidoEntity(pedido)).thenReturn(pedidoEntity);
        when(repository.save(pedidoEntity)).thenReturn(pedidoEntity);
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        // Test
        Pedido result = gateway.criaPedido(pedido);

        // Verification
        assertEquals(pedido, result);
        verify(mapper).paraPedidoEntity(pedido);
        verify(repository).save(pedidoEntity);
        verify(mapper).paraObjetoDominio(pedidoEntity);
    }

    @Test
    public void testAtualizaPedido() throws JsonProcessingException {
        // Mocking
        Pedido pedido = new Pedido();
        PedidoEntity pedidoEntity = new PedidoEntity();
        when(mapper.paraPedidoEntity(pedido)).thenReturn(pedidoEntity);

        // Test
        gateway.atualizaPedido(pedido);

        // Verification
        verify(mapper).paraPedidoEntity(pedido);
        verify(repository).save(pedidoEntity);
    }

    @Test
    public void testBuscaPedidos() throws JsonProcessingException {
        // Mocking
        PedidoEntity pedidoEntity1 = new PedidoEntity();
        PedidoEntity pedidoEntity2 = new PedidoEntity();
        List<PedidoEntity> pedidoEntities = Arrays.asList(pedidoEntity1, pedidoEntity2);
        when(repository.findAll()).thenReturn(pedidoEntities);

        Pedido pedido1 = new Pedido(1, new ArrayList<>(), StatusPedido.PREPARACAO, BigDecimal.valueOf(0.0), LocalTime.now());
        Pedido pedido2 = new Pedido(2, new ArrayList<>(), StatusPedido.PREPARACAO, BigDecimal.valueOf(0.0), LocalTime.now());
        when(mapper.paraObjetoDominio(pedidoEntity1)).thenReturn(pedido1);
        when(mapper.paraObjetoDominio(pedidoEntity2)).thenReturn(pedido2);

        // Test
        List<Pedido> result = gateway.buscaPedidos();

        // Verification
        assertEquals(2, result.size());
        assertTrue(result.contains(pedido1));
        assertTrue(result.contains(pedido2));
        verify(repository).findAll();
        verify(mapper, times(2)).paraObjetoDominio(any());
    }

    @Test
    public void testBuscaPedidoId() throws JsonProcessingException {
        // Mocking
        PedidoEntity pedidoEntity = new PedidoEntity();
        Optional<PedidoEntity> optionalPedidoEntity = Optional.of(pedidoEntity);
        when(repository.findById(anyInt())).thenReturn(optionalPedidoEntity);

        Pedido pedido = new Pedido();
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        // Test
        Pedido result = gateway.buscaPedidoId(1);

        // Verification
        assertEquals(pedido, result);
        verify(repository).findById(1);
        verify(mapper).paraObjetoDominio(pedidoEntity);
    }

    @Test
    public void testBuscaPedidosStatus() throws JsonProcessingException {
        // Mocking
        PedidoEntity pedidoEntity1 = new PedidoEntity();
        PedidoEntity pedidoEntity2 = new PedidoEntity();
        List<PedidoEntity> pedidoEntities = Arrays.asList(pedidoEntity1, pedidoEntity2);
        when(repository.findAll()).thenReturn(pedidoEntities);

        Pedido pedido1 =  new Pedido(1, new ArrayList<>(), StatusPedido.PREPARACAO, BigDecimal.valueOf(0.0), LocalTime.now());

        Pedido pedido2 =  new Pedido(2, new ArrayList<>(), StatusPedido.PREPARACAO, BigDecimal.valueOf(0.0), LocalTime.now());
        when(mapper.paraObjetoDominio(pedidoEntity1)).thenReturn(pedido1);
        when(mapper.paraObjetoDominio(pedidoEntity2)).thenReturn(pedido2);

        // Test
        List<Pedido> result = gateway.buscaPedidosStatus(StatusPedido.PREPARACAO);

        // Verification
        assertEquals(2, result.size());
        assertTrue(result.contains(pedido1));
        assertTrue(result.contains(pedido2));
        verify(repository).findAll();
        verify(mapper, times(2)).paraObjetoDominio(any());
    }
}