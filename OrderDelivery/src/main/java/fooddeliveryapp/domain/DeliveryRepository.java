package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "deliveries",
    path = "deliveries"
)
public interface DeliveryRepository
    extends PagingAndSortingRepository<Delivery, Long> {
    @Query(
        value = "select delivery " +
        "from Delivery delivery " +
        "where(:deliveryRiderId is null or delivery.deliveryRiderId = :deliveryRiderId)"
    )
    List<Delivery> deliveryListForRider(
        Long deliveryRiderId,
        Pageable pageable
    );

    @Query(
        value = "select delivery " +
        "from Delivery delivery " +
        "where(:deliveryId is null or delivery.deliveryId = :deliveryId)"
    )
    Delivery deliveryStatusDetails(Long deliveryId);
}
