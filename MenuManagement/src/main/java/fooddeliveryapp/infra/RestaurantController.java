package fooddeliveryapp.infra;

import fooddeliveryapp.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/restaurants")
@Transactional
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(
        value = "/restaurantsregistermenu",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant registerMenu(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RegisterMenuCommand registerMenuCommand
    ) throws Exception {
        System.out.println("##### /restaurant/registerMenu  called #####");
        Restaurant restaurant = new Restaurant();
        restaurant.registerMenu(registerMenuCommand);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(
        value = "/restaurants/{id}/updatemenu",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant updateMenu(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateMenuCommand updateMenuCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /restaurant/updateMenu  called #####");
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(
            id
        );

        optionalRestaurant.orElseThrow(() -> new Exception("No Entity Found"));
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.updateMenu(updateMenuCommand);

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(
        value = "/restaurants/{id}/deletemenu",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant deleteMenu(
        @PathVariable(value = "id") Long id,
        @RequestBody DeleteMenuCommand deleteMenuCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /restaurant/deleteMenu  called #####");
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(
            id
        );

        optionalRestaurant.orElseThrow(() -> new Exception("No Entity Found"));
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.deleteMenu(deleteMenuCommand);

        restaurantRepository.delete(restaurant);
        return restaurant;
    }
}
//>>> Clean Arch / Inbound Adaptor
