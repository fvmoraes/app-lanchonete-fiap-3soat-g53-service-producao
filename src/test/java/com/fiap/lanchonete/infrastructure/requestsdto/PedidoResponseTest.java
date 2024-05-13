package com.fiap.lanchonete.infrastructure.requestsdto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoResponseTest {

    @Test
    public void testGetIdPedido() {
        Integer idPedido = 123;
        PedidoResponse response = new PedidoResponse(idPedido, null, null, null);

       assertEquals(idPedido, response.getIdPedido());
    }

    @Test
    public void testGetListaProdutos() {
        List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
        
        PedidoResponse response = new PedidoResponse(null, produtos, null, null);

        assertEquals(produtos, response.getListaProdutos());
    }

    @Test
    public void testGetStatusPedido() {
        StatusPedido status = StatusPedido.PREPARACAO;
        PedidoResponse response = new PedidoResponse(null, null, status, null);

        assertEquals(status, response.getStatusPedido());
    }

    @Test
    public void testGetValorTotal() {
        BigDecimal valorTotal = new BigDecimal("50.00");

        PedidoResponse response = new PedidoResponse(null, null, null, valorTotal);

        assertEquals(valorTotal, response.getValorTotal());
    }
}
