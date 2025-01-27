package cat.nova.order_service.controller;

import cat.nova.order_service.dto.Order;
import cat.nova.order_service.dto.OrderEvent;
import cat.nova.order_service.publisher.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }
    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent event = new OrderEvent();
        event.setStatus("PENDING");
        event.setMessage("Order is in progress");
        event.setOrder(order);
        orderProducer.sendMessage(event);
        return "Order sent to Queue";

    }
}
