package cat.nova.order_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.orderqueue.name}")
    private String orderQueue;
    @Value("${rabbitmq.emailqueue.name}")
    private String emailQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.binding.routingkey}")
    private String orderRoutingKey;
    @Value("${rabbitmq.binding.email.routingkey}")
    private String emailRoutingKey;
    //spring bean for queue
    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueue);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue);
    }
    //spring bean for exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }
    //spring bean for exchange and queue using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(orderQueue()).to(exchange()).with(orderRoutingKey);
    }

    @Bean
    public Binding emailbinding(){
        System.out.println("Email event sent");
        return BindingBuilder.bind(emailQueue()).to(exchange()).with(emailRoutingKey);
    }

    //message converter
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    // configure rabbit template
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
