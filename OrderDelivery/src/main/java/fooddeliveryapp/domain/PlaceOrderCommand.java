package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PlaceOrderCommand {

    private Long customerId;
    private RestaurantId restaurantId;
    private List<String> menuItems;
    private Double orderAmount;
    private String paymentId;
}
