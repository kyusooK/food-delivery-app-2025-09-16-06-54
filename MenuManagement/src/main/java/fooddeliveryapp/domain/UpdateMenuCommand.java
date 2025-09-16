package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateMenuCommand {

    private Long restaurantId;
    private Long menuId;
    private String name;
    private Double price;
    private String description;
    private String image;
}
