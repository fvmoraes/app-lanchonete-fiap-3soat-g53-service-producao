package com.fiap.lanchonete.infrastructure.gateway;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.event.PedidoRealizadoEvent;

public class ProducaoConsumerGatewayTest {

    private ProducaoConsumerGateway producaoConsumerGateway;

    @Mock
    private PedidoUseCases pedidoUseCases;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        producaoConsumerGateway = new ProducaoConsumerGateway(pedidoUseCases);
    }

    @Test
    public void testPedidoRecebido() throws PedidoNaoEncontradoException {
        // Arrange
        PedidoRealizadoEvent pedidoRealizado = new PedidoRealizadoEvent(new Pedido());
        
        // Act
        producaoConsumerGateway.pedidoRecebido(pedidoRealizado);
        
        // Assert
        verify(pedidoUseCases, times(1)).criaPedido(pedidoRealizado.getPedidoRealizado());
    }

    // Test other scenarios as needed
}
