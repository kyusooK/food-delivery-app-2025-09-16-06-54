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
    collectionResourceRel = "restaurants",
    path = "restaurants"
)
public interface RestaurantRepository
    extends PagingAndSortingRepository<Restaurant, Long> {
    @Query(
        value = "select restaurant " +
        "from Restaurant restaurant " +
        "where(:restaurantId is null or restaurant.restaurantId = :restaurantId)"
    )
    List<Restaurant> menuList(Long restaurantId, Pageable pageable);

    @Query(
        value = "select restaurant " +
        "from Restaurant restaurant " +
        "where(:restaurantId is null or restaurant.restaurantId = :restaurantId) and (:menuId is null or restaurant.menuId = :menuId)"
    )
    Restaurant menuDetails(Long restaurantId, Long menuId);
}
