package za.co.mkhungo.strategy.impl;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import za.co.mkhungo.controller.OrderController;
import za.co.mkhungo.exception.OrderNotFoundException;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class OrderControllerLinkProvider implements ControllerLinkProvider {
    /**
     * @param id
     * @return
     */
    @Override
    public Link getSelfLink(Long id) {
        try {
            return linkTo(methodOn(OrderController.class).getOrderById(id)).withSelfRel();
        } catch (OrderNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public Link getAllLinks() {
        return linkTo(methodOn(OrderController.class).getOrders()).withRel("orders");
    }
}
