package fooddeliveryapp.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public enum DeliveryStatus {
    WAITING_FOR_PICKUP,
    IN_DELIVERY,
    DELIVERED,
}
