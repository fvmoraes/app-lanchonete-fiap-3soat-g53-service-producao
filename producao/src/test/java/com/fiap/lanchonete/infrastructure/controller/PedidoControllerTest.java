package com.fiap.lanchonete.infrastructure.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPedido;
import com.fiap.lanchonete.infrastructure.mapper.PedidoRequestMapper;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

@AutoConfigureMockMvc
@SpringBootTest
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoUseCases pedidoUseCases;

    private PedidoRequestMapper mapper = new PedidoRequestMapper();

    @MockBean
    private RabbitTemplate template;
  
    
    @Test
    void buscaPedidos() throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        when(pedidoUseCases.buscaPedidos()).thenReturn(pedidos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/producao/pedido")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void buscaProximoPedido() throws Exception {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        when(pedidoUseCases.buscaProximoPedido()).thenReturn(pedido);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/producao/pedido/proximo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
 
    @Test
    void buscaPedidoPorId_Encontrado() throws Exception {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        when(pedidoUseCases.buscaPedidoId(1)).thenReturn(pedido);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/producao/pedido/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void buscaPedidoPorId_NaoEncontrado() throws Exception {
        when(pedidoUseCases.buscaPedidoId(1)).thenThrow(PedidoNaoEncontradoException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/producao/pedido/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void buscaPedidosPorStatus() throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        when(pedidoUseCases.buscaPedidosPorStatus(StatusPedido.RECEBIDO)).thenReturn(pedidos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/producao/pedido/status/RECEBIDO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void atualizaPedidosStatus() throws Exception {
        Pedido pedido = new Pedido(1, new ArrayList<>(), StatusPedido.RECEBIDO, BigDecimal.valueOf(0.0), LocalTime.now());
        when(pedidoUseCases.atualizaPedidoStatus(1, StatusPedido.PREPARACAO)).thenReturn(pedido);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/producao/pedido/1/PREPARACAO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
