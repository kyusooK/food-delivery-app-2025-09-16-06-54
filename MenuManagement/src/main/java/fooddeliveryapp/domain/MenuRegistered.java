package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuRegistered extends AbstractEvent {

    private Long restaurantId;
    private Long menuId;
    private String name;
    private Double price;
    private String description;
    private String image;
    private Date createdAt;

    public MenuRegistered(Restaurant aggregate) {
        super(aggregate);
    }

    public MenuRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
