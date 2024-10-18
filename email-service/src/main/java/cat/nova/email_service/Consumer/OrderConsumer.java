package cat.nova.email_service.Consumer;

import cat.nova.email_service.dto.Order;
import cat.nova.email_service.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    @RabbitListener(queues = "${rabbitmq.emailqueue.name}")
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event received -> %s",orderEvent));

        //email service need to send an email to customer
    }
}
