package com.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CategoriaTest {

    @Test
    public void testEnumValues() {
        assertEquals("Lanche", Categoria.Lanche.name());
        assertEquals("Bebida", Categoria.Bebida.name());
        assertEquals("Acompanhamento", Categoria.Acompanhamento.name());
        assertEquals("Sobremesa", Categoria.Sobremesa.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, Categoria.Lanche.ordinal());
        assertEquals(1, Categoria.Bebida.ordinal());
        assertEquals(2, Categoria.Acompanhamento.ordinal());
        assertEquals(3, Categoria.Sobremesa.ordinal());
    }

    @Test
    public void testEnumToString() {
        assertEquals("Lanche", Categoria.Lanche.toString());
        assertEquals("Bebida", Categoria.Bebida.toString());
        assertEquals("Acompanhamento", Categoria.Acompanhamento.toString());
        assertEquals("Sobremesa", Categoria.Sobremesa.toString());
    }
}
