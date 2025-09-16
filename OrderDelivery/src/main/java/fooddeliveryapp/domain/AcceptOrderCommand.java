package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class AcceptOrderCommand {

    private Long orderId;
    private RestaurantId restaurantId;
}
