package com.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PedidoTest {

    @Test
    public void testGetId() {
        Integer id = 1;
        Pedido pedido = new Pedido(id, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        assertEquals(id, pedido.getId());
    }

    @Test
    public void testSetId() {
        Integer id = 2;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        assertEquals(id, pedido.getId());
    }

    @Test
    public void testGetListaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto());
        Pedido pedido = new Pedido(1, produtos, StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        assertEquals(produtos, pedido.getListaProdutos());
    }

    @Test
    public void testSetListaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto());
        Pedido pedido = new Pedido();
        pedido.setListaProdutos(produtos);
        assertEquals(produtos, pedido.getListaProdutos());
    }

    @Test
    public void testGetStatusPedido() {
        StatusPedido statusPedido = StatusPedido.PREPARACAO;
        Pedido pedido = new Pedido(1, new ArrayList<>(), statusPedido, BigDecimal.valueOf(0.0), LocalTime.now());
        assertEquals(statusPedido, pedido.getStatusPedido());
    }

    @Test
    public void testSetStatusPedido() {
        StatusPedido statusPedido = StatusPedido.FINALIZADO;
        Pedido pedido = new Pedido();
        pedido.setStatusPedido(statusPedido);
        assertEquals(statusPedido, pedido.getStatusPedido());
    }

    @Test
    public void testGetValorTotal() {
        BigDecimal valorTotal = BigDecimal.valueOf(25.0);
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, valorTotal, LocalTime.now());
        assertEquals(valorTotal, pedido.getValorTotal());
    }

    @Test
    public void testSetValorTotal() {
        BigDecimal valorTotal = BigDecimal.valueOf(30.0);
        Pedido pedido = new Pedido();
        pedido.setValorTotal(valorTotal);
        assertEquals(valorTotal, pedido.getValorTotal());
    }

    @Test
    public void testGetDataCriacao() {
        LocalTime dataCriacao = LocalTime.now();
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), dataCriacao);
        assertEquals(dataCriacao, pedido.getDataCricao());
    }

    @Test
    public void testSetDataCriacao() {
        LocalTime dataCriacao = LocalTime.now();
        Pedido pedido = new Pedido();
        pedido.setDataCricao(dataCriacao);
        assertEquals(dataCriacao, pedido.getDataCricao());
    }

    @Test
    public void testCompareTo() {
        Pedido pedido1 = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.of( 5, 12, 10));
        Pedido pedido2 = new Pedido(2, new ArrayList<>(), StatusPedido.PREPARACAO, BigDecimal.valueOf(0.0), LocalTime.of( 5, 12, 11));
        assertTrue(pedido1.compareTo(pedido2) < 0);
    }
}
