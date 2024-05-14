package com.fiap.lanchonete.domain.entity.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoAtualizadoEventTest {

    @Test
    public void testConstructorAndGetters() {
        int id = 1;
        StatusPedido status = StatusPedido.PREPARACAO;

        PedidoAtualizadoEvent pedidoAtualizadoEvent = new PedidoAtualizadoEvent(id, status);

        assertEquals(id, pedidoAtualizadoEvent.getId());
        assertEquals(status, pedidoAtualizadoEvent.getStatus());
    }

    @Test
    public void testSetters() {
        PedidoAtualizadoEvent pedidoAtualizadoEvent = new PedidoAtualizadoEvent();
        int id = 2;
        StatusPedido status = StatusPedido.FINALIZADO;

        pedidoAtualizadoEvent.setId(id);
        pedidoAtualizadoEvent.setStatus(status);

        assertEquals(id, pedidoAtualizadoEvent.getId());
        assertEquals(status, pedidoAtualizadoEvent.getStatus());
    }

    // Test other scenarios as needed
}
