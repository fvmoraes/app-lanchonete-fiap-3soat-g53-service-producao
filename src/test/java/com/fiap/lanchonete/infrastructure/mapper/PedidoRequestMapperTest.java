package com.fiap.lanchonete.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

public class PedidoRequestMapperTest {

    @Test
    public void testParaResponse() {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setListaProdutos(new ArrayList<>());
        pedido.setStatusPedido(StatusPedido.PREPARACAO);
        pedido.setValorTotal(BigDecimal.valueOf(25));

        PedidoRequestMapper mapper = new PedidoRequestMapper();

        PedidoResponse response = mapper.paraResponse(pedido);

        assertEquals(pedido.getListaProdutos(), response.getListaProdutos());
        assertEquals(pedido.getStatusPedido(), response.getStatusPedido());
        assertEquals(pedido.getValorTotal(), response.getValorTotal());
    }

}
