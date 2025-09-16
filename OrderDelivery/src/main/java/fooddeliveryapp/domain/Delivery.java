package fooddeliveryapp.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooddeliveryapp.OrderDeliveryApplication;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryId;

    private Long deliveryRiderId;

    private DeliveryStatus deliveryStatus;

    private Date deliveryAcceptedAt;

    private Date deliveryPickedUpAt;

    private Date deliveryCompletedAt;

    private Boolean deliveryNotificationSent;

    private Boolean deliveryDuplicatePrevented;

    @Embedded
    private OrderId orderId;

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = OrderDeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public void acceptDelivery(AcceptDeliveryCommand acceptDeliveryCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void pickupDelivery(PickupDeliveryCommand pickupDeliveryCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void completeDelivery(
        CompleteDeliveryCommand completeDeliveryCommand
    ) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
