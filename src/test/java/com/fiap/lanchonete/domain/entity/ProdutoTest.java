package com.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ProdutoTest {
	@Test
    public void testGetCategoria() {
        Categoria categoria = Categoria.Lanche;
        Produto produto = new Produto(categoria, "Hambúrguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.0));
        assertEquals(categoria, produto.getCategoria());
    }

    @Test
    public void testSetCategoria() {
        Categoria categoria = Categoria.Bebida;
        Produto produto = new Produto();
        produto.setCategoria(categoria);
        assertEquals(categoria, produto.getCategoria());
    }

    @Test
    public void testGetNome() {
        String nome = "Batata Frita";
        Produto produto = new Produto(Categoria.Acompanhamento, nome, "Batatas crocantes", BigDecimal.valueOf(5.0));
        assertEquals(nome, produto.getNome());
    }

    @Test
    public void testSetNome() {
        String nome = "Coca-Cola";
        Produto produto = new Produto();
        produto.setNome(nome);
        assertEquals(nome, produto.getNome());
    }

    @Test
    public void testGetDescricao() {
        String descricao = "Refrigerante";
        Produto produto = new Produto(Categoria.Bebida, "Coca-Cola", descricao, BigDecimal.valueOf(4.0));
        assertEquals(descricao, produto.getDescricao());
    }

    @Test
    public void testSetDescricao() {
        String descricao = "Suco de laranja";
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        assertEquals(descricao, produto.getDescricao());
    }

    @Test
    public void testGetValor() {
        BigDecimal valor = BigDecimal.valueOf(15.0);
        Produto produto = new Produto(Categoria.Sobremesa, "Sorvete", "Sorvete de chocolate", valor);
        assertEquals(valor, produto.getValor());
    }

    @Test
    public void testSetValor() {
        BigDecimal valor = BigDecimal.valueOf(3.5);
        Produto produto = new Produto();
        produto.setValor(valor);
        assertEquals(valor, produto.getValor());
    }
}
