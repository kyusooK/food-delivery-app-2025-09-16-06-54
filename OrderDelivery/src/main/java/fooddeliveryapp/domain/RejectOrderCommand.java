package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RejectOrderCommand {

    private Long orderId;
    private RestaurantId restaurantId;
    private String rejectionReason;
}
