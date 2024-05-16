package com.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class StatusPagamentoTest {

    @Test
    public void testEnumValues() {
        assertEquals("Pago", StatusPagamento.PAGO.name());
        assertEquals("PENDENTE", StatusPagamento.PENDENTE.name());
        assertEquals("Cancelado", StatusPagamento.CANCELADO.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, StatusPagamento.PAGO.ordinal());
        assertEquals(1, StatusPagamento.PENDENTE.ordinal());
        assertEquals(2, StatusPagamento.CANCELADO.ordinal());
    }

    @Test
    public void testEnumToString() {
        assertEquals("Pago", StatusPagamento.PAGO.toString());
        assertEquals("PENDENTE", StatusPagamento.PENDENTE.toString());
        assertEquals("Cancelado", StatusPagamento.CANCELADO.toString());
    }
}
