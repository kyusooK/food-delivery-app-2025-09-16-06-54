package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeleteMenuCommand {

    private Long restaurantId;
    private Long menuId;
}
