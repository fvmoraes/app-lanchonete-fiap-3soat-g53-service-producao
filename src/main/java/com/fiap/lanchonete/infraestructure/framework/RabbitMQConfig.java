package com.fiap.lanchonete.infraestructure.framework;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	private static final String PEDIDO_EXCHANGE_1 = "pedido-exchange";
	private static final String PRODUCAO_PEDIDO_ROUTING_KEY = "producao-para-pedido-routing-key";

	private static final String PEDIDO_QUEUE_1 = "pedido-queue";
	private static final String PRODUCAO_QUEUE_1 = "producao-queue";

	private static final String PEDIDO_DLQ = "pedido-queue-dlq";

    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    
	
	@Bean
	DirectExchange pedidoExchange() {
		return ExchangeBuilder
			.directExchange(PEDIDO_EXCHANGE_1)
			.build();
	}

	@Bean
	Queue pedidoQueue() {
		return QueueBuilder.nonDurable(PEDIDO_QUEUE_1).withArgument("x-dead-letter-exchange", "")
				.withArgument("x-dead-letter-routing-key", PEDIDO_DLQ).build();
	}

	@Bean
	Queue pedidoDLQ() {
		return QueueBuilder.nonDurable(PEDIDO_DLQ).build();
	}

	@Bean
	Queue producaoQueue() {
		return QueueBuilder
			.nonDurable(PRODUCAO_QUEUE_1)
			.build();
	}
	@Bean
	Binding pedidoBinding(Queue pedidoQueue, DirectExchange pedidoExchange) {
		return BindingBuilder
			.bind(pedidoQueue)
			.to(pedidoExchange)
			.with(PRODUCAO_PEDIDO_ROUTING_KEY);
	}

}
