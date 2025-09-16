package fooddeliveryapp.infra;

import fooddeliveryapp.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class RestaurantHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Restaurant>> {

    @Override
    public EntityModel<Restaurant> process(EntityModel<Restaurant> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "registermenu")
                .withRel("egistermenu")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/updatemenu")
                .withRel("updatemenu")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/deletemenu")
                .withRel("deletemenu")
        );

        return model;
    }
}
