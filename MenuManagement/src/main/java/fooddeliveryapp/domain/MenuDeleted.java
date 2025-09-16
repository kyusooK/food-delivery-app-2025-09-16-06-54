package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuDeleted extends AbstractEvent {

    private Long restaurantId;
    private Long menuId;
    private Date deletedAt;

    public MenuDeleted(Restaurant aggregate) {
        super(aggregate);
    }

    public MenuDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
