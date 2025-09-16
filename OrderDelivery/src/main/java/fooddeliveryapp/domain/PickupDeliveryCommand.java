package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PickupDeliveryCommand {

    private Long deliveryId;
    private Long deliveryRiderId;
}
