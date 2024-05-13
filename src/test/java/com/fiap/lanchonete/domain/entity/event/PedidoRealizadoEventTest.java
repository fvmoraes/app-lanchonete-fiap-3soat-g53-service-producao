package com.fiap.lanchonete.domain.entity.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Pedido;

public class PedidoRealizadoEventTest {

    @Test
    public void testGetSetPedidoRealizado() {
        Pedido pedido = new Pedido();

        PedidoRealizadoEvent evento = new PedidoRealizadoEvent();
        evento.setPedidoRealizado(pedido);

        assertEquals(pedido, evento.getPedidoRealizado());
    }
}
