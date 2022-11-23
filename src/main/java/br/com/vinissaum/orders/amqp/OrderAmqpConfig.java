package br.com.vinissaum.orders.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAmqpConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory conn, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(conn);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }

    @Bean
    public Queue orderDetailQueue() {
        return QueueBuilder.nonDurable("payment.detail-order") //
                .deadLetterExchange("payment.dlx") //
                .build();
    }

    @Bean
    public Queue orderDetailDeadLetterQueue() {
        return QueueBuilder.nonDurable("payment.detail-order-dlq").build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("payment.ex").build();
    }

    @Bean
    public FanoutExchange deadLetterExchange() {
        return ExchangeBuilder.fanoutExchange("payment.dlx").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(orderDetailQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingDlx() {
        return BindingBuilder.bind(orderDetailDeadLetterQueue()).to(deadLetterExchange());
    }

}
