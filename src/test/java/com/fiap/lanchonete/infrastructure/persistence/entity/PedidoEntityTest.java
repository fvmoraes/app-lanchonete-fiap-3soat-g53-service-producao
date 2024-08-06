package com.fiap.lanchonete.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.StatusPedido;

public class PedidoEntityTest {

    private PedidoEntity pedidoEntity;

    @BeforeEach
    public void setUp() {
        pedidoEntity = new PedidoEntity();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(pedidoEntity);
        assertNull(pedidoEntity.getId());
        assertNull(pedidoEntity.getListaProdutosPedido());
        assertNull(pedidoEntity.getStatusPedido());
        assertNull(pedidoEntity.getValorTotal());
        assertNotNull(pedidoEntity.getDataCricao());
    }

    @Test
    public void testParameterizedConstructor() {
        String listaProdutos = "produto1, produto2";
        StatusPedido status = StatusPedido.PREPARACAO;
        BigDecimal valor = BigDecimal.valueOf(100.0);

        PedidoEntity pedido = new PedidoEntity(listaProdutos, status, valor);

        assertEquals(listaProdutos, pedido.getListaProdutosPedido());
        assertEquals(status, pedido.getStatusPedido());
        assertEquals(valor, pedido.getValorTotal());
        assertNotNull(pedido.getDataCricao());
    }

    @Test
    public void testFullParameterizedConstructor() {
        Integer id = 1;
        String listaProdutos = "produto1, produto2";
        StatusPedido status = StatusPedido.PREPARACAO;
        BigDecimal valor = BigDecimal.valueOf(100.0);

        PedidoEntity pedido = new PedidoEntity(id, listaProdutos, status, valor);

        assertEquals(id, pedido.getId());
        assertEquals(listaProdutos, pedido.getListaProdutosPedido());
        assertEquals(status, pedido.getStatusPedido());
        assertEquals(valor, pedido.getValorTotal());
        assertNotNull(pedido.getDataCricao());
    }

    @Test
    public void testSettersAndGetters() {
        Integer id = 1;
        String listaProdutos = "produto1, produto2";
        StatusPedido status = StatusPedido.FINALIZADO;
        BigDecimal valor = BigDecimal.valueOf(150.0);
        LocalTime dataCriacao = LocalTime.now();

        pedidoEntity.setId(id);
        pedidoEntity.setListaProdutosPedido(listaProdutos);
        pedidoEntity.setStatusPedido(status);
        pedidoEntity.setValorTotal(valor);
        pedidoEntity.setDataCricao(dataCriacao);

        assertEquals(id, pedidoEntity.getId());
        assertEquals(listaProdutos, pedidoEntity.getListaProdutosPedido());
        assertEquals(status, pedidoEntity.getStatusPedido());
        assertEquals(valor, pedidoEntity.getValorTotal());
        assertEquals(dataCriacao, pedidoEntity.getDataCricao());
    }
}
