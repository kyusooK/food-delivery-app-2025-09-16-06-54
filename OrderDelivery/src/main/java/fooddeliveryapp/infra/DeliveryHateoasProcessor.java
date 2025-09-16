package fooddeliveryapp.infra;

import fooddeliveryapp.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class DeliveryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Delivery>> {

    @Override
    public EntityModel<Delivery> process(EntityModel<Delivery> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "acceptdelivery")
                .withRel("cceptdelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/pickupdelivery")
                .withRel("pickupdelivery")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/completedelivery"
                )
                .withRel("completedelivery")
        );

        return model;
    }
}
