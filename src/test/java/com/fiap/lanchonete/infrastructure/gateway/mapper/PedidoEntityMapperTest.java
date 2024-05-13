package com.fiap.lanchonete.infrastructure.gateway.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

public class PedidoEntityMapperTest {

    @Test
    public void testParaPedidoEntity() throws JsonProcessingException {
        Pedido pedido = mock(Pedido.class);
        List<Produto> produtos = new ArrayList<>();
        when(pedido.getId()).thenReturn(1);
        when(pedido.getListaProdutos()).thenReturn(produtos);
        when(pedido.getStatusPedido()).thenReturn(StatusPedido.RECEBIDO);
        when(pedido.getValorTotal()).thenReturn(BigDecimal.valueOf(25.0));

        PedidoEntityMapper mapper = new PedidoEntityMapper();
        PedidoEntity pedidoEntity = mapper.paraPedidoEntity(pedido);

        assertEquals(1, pedidoEntity.getId());

        assertEquals(BigDecimal.valueOf(25.0), pedidoEntity.getValorTotal());
    }

    @Test
    public void testParaObjetoDominio() throws JsonMappingException, JsonProcessingException {
        PedidoEntity pedidoEntity = mock(PedidoEntity.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Produto> produtos = new ArrayList<>();
        String produtosJson = objectMapper.writeValueAsString(produtos);
        when(pedidoEntity.getId()).thenReturn(1);
        when(pedidoEntity.getListaProdutosPedido()).thenReturn(produtosJson);
        when(pedidoEntity.getStatusPedido()).thenReturn(StatusPedido.RECEBIDO);
        when(pedidoEntity.getValorTotal()).thenReturn(BigDecimal.valueOf(25.0));

        PedidoEntityMapper mapper = new PedidoEntityMapper();
        Pedido pedido = mapper.paraObjetoDominio(pedidoEntity);

        assertEquals(1, pedido.getId());
        assertEquals(BigDecimal.valueOf(25.0), pedido.getValorTotal());
    }
}
