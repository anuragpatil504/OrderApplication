package cat.nova.stock_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String orderId;
    private String orderName;
    private String quantity;
    private double price;
}
