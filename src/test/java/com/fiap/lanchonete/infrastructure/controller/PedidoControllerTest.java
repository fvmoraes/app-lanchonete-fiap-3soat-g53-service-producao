package com.fiap.lanchonete.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.mapper.PedidoRequestMapper;

public class PedidoControllerTest {


    @Mock
    private PedidoUseCases pedidoUseCases;

    @Mock
    private PedidoRequestMapper mapper;
    
    @Mock
    private RabbitTemplate template;

    @InjectMocks
    private PedidoController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscaPedidos() {
        // Mocking
        List<Pedido> pedidos = Arrays.asList(new Pedido(), new Pedido());
        when(pedidoUseCases.buscaPedidos()).thenReturn(pedidos);

        // Test
        controller.buscaPedidos();

        // Verification
        verify(pedidoUseCases).buscaPedidos();
        verify(mapper, times(2)).paraResponse(any());
    }

    @Test
    public void testCriaPedidos() {
        // Mocking
        Pedido pedido = new Pedido();
        when(pedidoUseCases.criaPedido(any())).thenReturn(pedido);

        // Test
        controller.criaPedidos(new Pedido());

        // Verification
        verify(pedidoUseCases).criaPedido(any());
        verify(mapper).paraResponse(any());
    }

    @Test
    public void testBuscaPoximoPedido() {
        // Mocking
        Pedido pedido = new Pedido();
        when(pedidoUseCases.buscaProximoPedido()).thenReturn(pedido);

        // Test
        controller.buscaPoximoPedido();

        // Verification
        verify(pedidoUseCases).buscaProximoPedido();
        verify(mapper).paraResponse(any());
    }

    @Test
    public void testBuscaPedidosPorId() throws PedidoNaoEncontradoException {
        // Mocking
        Pedido pedido = new Pedido();
        when(pedidoUseCases.buscaPedidoId(anyInt())).thenReturn(pedido);

        // Test
        controller.buscaPedidosPorId(1);

        // Verification
        verify(pedidoUseCases).buscaPedidoId(anyInt());
        verify(mapper).paraResponse(any());
    }

    @Test
    public void testBuscaPedidosPorStatus() {
        // Mocking
        List<Pedido> pedidos = Arrays.asList(new Pedido(), new Pedido());
        when(pedidoUseCases.buscaPedidosPorStatus(any())).thenReturn(pedidos);

        // Test
        controller.buscaPedidosPorStatus(StatusPedido.PREPARACAO);

        // Verification
        verify(pedidoUseCases).buscaPedidosPorStatus(any());
        verify(mapper, times(2)).paraResponse(any());
    }

    @Test
    public void testAtualizaPedidosStatus() {
        // Mocking
        Pedido pedido = new Pedido();
        when(pedidoUseCases.atualizaPedidoStatus(anyInt(), any())).thenReturn(pedido);

        // Test
        controller.atualizaPedidosStatus(StatusPedido.PREPARACAO, 1);

        // Verification
        verify(pedidoUseCases).atualizaPedidoStatus(anyInt(), any());
        verify(mapper).paraResponse(any());
    }

}
