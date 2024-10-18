package cat.nova.order_service.publisher;

import cat.nova.order_service.dto.Order;
import cat.nova.order_service.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.binding.routingkey}")
    private String orderRoutingKey;

    @Value("${rabbitmq.binding.email.routingkey}")
    private String emailRoutingKey;
    private RabbitTemplate rabbitTemplate;
    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event sent to Queue -> %s",orderEvent));
        rabbitTemplate.convertAndSend(exchange,orderRoutingKey,orderEvent);
        LOGGER.info(String.format("Order event sent to Email queue -> %s",orderEvent));
        rabbitTemplate.convertAndSend(exchange,emailRoutingKey,orderEvent);
    }




}
