package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RegisterMenuCommand {

    private Long restaurantId;
    private String name;
    private Double price;
    private String description;
    private String image;
}
