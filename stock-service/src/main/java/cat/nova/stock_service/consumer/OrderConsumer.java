package cat.nova.stock_service.consumer;

import cat.nova.stock_service.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {


    private Logger LOGGER =  LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${rabbitmq.orderqueue.name}")
    public  void consume(OrderEvent event){
        LOGGER.info(String.format("Order event received -> %s",event));
        //save event in db
    }
}
