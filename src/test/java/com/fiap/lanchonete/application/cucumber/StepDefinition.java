package com.fiap.lanchonete.application.cucumber;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPedido;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

public class StepDefinition {
	private final String ENDPOINT_API = "http://localhost:8080/pedido/api/v1/producao/pedido";

	private Response response;

	@Quando("efeturar uma requisicao para Buscar todos os pedidos")
	public void efeturar_uma_requisicao_para_buscar_todos_os_pedidos() {
		response = given().when().get(ENDPOINT_API);

	}

	@Entao("deve retornar uma lista com os pedidos cadastrados")
	public void deve_retornar_uma_lista_com_os_pedidos_cadastrados() {
		response.then().statusCode(200).extract().as(List.class);
	}

	@Quando("efetuar uma requisicao para Criar um Pedido")
	public void efetuar_uma_requisicao_para_criar_um_pedido() {
		List<Produto> listaProdutos = new ArrayList<>();
		StatusPedido statusPedido = StatusPedido.RECEBIDO;
		Pedido pedido = new Pedido(958, listaProdutos, statusPedido, BigDecimal.valueOf(10), LocalTime.now());

		response = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(pedido).when().post(ENDPOINT_API);

	}

	@Entao("deve retornar o Pedido cadastrado")
	public void deve_retornar_o_pedido_cadastrado() {
		response.then().body(containsString("RECEBIDO"));
	}

	@Quando("efetuar requisicao para buscar o Proximo pedido da fila")
	public void efetuar_requisicao_para_buscar_o_proximo_pedido_da_fila() {
		response = given().when().get(ENDPOINT_API + "/proximo");

	}

	@Entao("deve retorna um Pedido")
	public void deve_retorna_um_pedido() {
		response.then().statusCode(HttpStatus.OK.value());

	}

	@Quando("efetuar requisicao de buscar Pedido por Id")
	public void efetuar_requisicao_de_buscar_pedido_por_id() {

		response = given().when().get(ENDPOINT_API + "/{id}", 958);

	}

	@Entao("retorna o pedido")
	public void retorna_o_pedido() {

		response.then().body(containsString("\"idPedido\" : 958"));
	}

	@Entao("retorna null com status no content")
	public void retorna_null_com_status_no_content() {
		response.then().statusCode(HttpStatus.NO_CONTENT.value());
	}

	@Quando("efetuar requisicao de buscar Pedido por Status")
	public void efetuar_requisicao_de_buscar_pedido_por_status() {
		response = given().when().get(ENDPOINT_API + "/status/{status}", StatusPedido.RECEBIDO);
	}

	@Entao("retorna uma Lista de pedidos com o status")
	public void retorna_uma_lista_de_pedidos_com_o_status() {
		response.then().statusCode(200).extract().as(List.class);
	}

	@Quando("efetuar requisicao de atualizar o status de um pedido")
	public void efetuar_requisicao_de_atualizar_o_status_de_um_pedido() {
		response = given().when().get(ENDPOINT_API + "/{id}/{status}", 958, StatusPedido.PRONTO);

	}

	@Entao("retorna com o Pedido atualizado")
	public void retorna_com_o_pedido_atualizado() {
		response.then().body(containsString("PRONTO"));

	}

	@Quando("efetuar requisicao de buscar Pedido nao existente por Id")
	public void efetuar_requisicao_de_buscar_pedido_nao_existente_por_id() {
		response = given().when().get(ENDPOINT_API + "/{id}", 15858);

	}

}
