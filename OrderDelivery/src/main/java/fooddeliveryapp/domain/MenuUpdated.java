package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class MenuUpdated extends AbstractEvent {

    private Long restaurantId;
    private Long menuId;
    private String name;
    private Double price;
    private String description;
    private String image;
    private Date updatedAt;
}
