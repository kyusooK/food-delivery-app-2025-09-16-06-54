package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PrepareOrderCommand {

    private Long orderId;
    private RestaurantId restaurantId;
}
