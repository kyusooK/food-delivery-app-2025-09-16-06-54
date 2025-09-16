package fooddeliveryapp.domain;

import java.util.Date;
import lombok.Data;

@Data
public class MenuDetailsQuery {

    private Long restaurantId;
    private Long menuId;
}
