package com.fiap.lanchonete.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoUseCasesImpTest {

	@Mock
    private PedidoGateway pedidoGateway;
	@Mock
    private PedidoUseCasesImp pedidoUseCases;

    @BeforeEach
    public void setUp() {
        pedidoGateway = mock(PedidoGateway.class);
        pedidoUseCases = new PedidoUseCasesImp(pedidoGateway);
    }

    @Test
    public void testBuscaPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add( new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now()));
        when(pedidoGateway.buscaPedidos()).thenReturn(pedidos);

        assertEquals(pedidos, pedidoUseCases.buscaPedidos());
    }

    @Test
    public void testBuscaPedidosPorStatus() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add( new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now()));

        when(pedidoGateway.buscaPedidosStatus(StatusPedido.RECEBIDO)).thenReturn(pedidos);

        assertEquals(pedidos, pedidoUseCases.buscaPedidosPorStatus(StatusPedido.RECEBIDO));
    }

    @Test
    public void testCriaPedido() {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        when(pedidoGateway.criaPedido(pedido)).thenReturn(pedido);

        assertEquals(pedido, pedidoUseCases.criaPedido(pedido));
    }

    @Test
    public void testBuscaPedidoId_PedidoNaoEncontrado() throws PedidoNaoEncontradoException {
        when(pedidoGateway.buscaPedidoId(anyInt())).thenReturn(null);
        assertThrows(PedidoNaoEncontradoException.class, () -> pedidoUseCases.buscaPedidoId(1)) ;
        
    }

    @Test
    public void testBuscaPedidoId() throws PedidoNaoEncontradoException {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(pedido);

        assertEquals(pedido, pedidoUseCases.buscaPedidoId(1));
    }



    @Test
    public void testBuscaProximoPedido() throws PedidoNaoEncontradoException {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);
        when(pedidoGateway.buscaPedidosStatus(StatusPedido.RECEBIDO)).thenReturn(pedidos);

        assertEquals(pedido, pedidoUseCases.buscaProximoPedido());
    }

    @Test
    public void testAtualizaPedidoStatus() {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(pedido);

        assertEquals(pedido, pedidoUseCases.atualizaPedidoStatus(1, StatusPedido.PREPARACAO));
        assertEquals(StatusPedido.PREPARACAO, pedido.getStatusPedido());
        verify(pedidoGateway, times(1)).atualizaPedido(pedido);
    }
}
