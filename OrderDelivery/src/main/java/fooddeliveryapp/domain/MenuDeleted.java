package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class MenuDeleted extends AbstractEvent {

    private Long restaurantId;
    private Long menuId;
    private Date deletedAt;
}
