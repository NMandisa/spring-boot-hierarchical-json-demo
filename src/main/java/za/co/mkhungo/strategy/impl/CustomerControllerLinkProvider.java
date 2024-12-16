package za.co.mkhungo.strategy.impl;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import za.co.mkhungo.controller.CustomerController;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class CustomerControllerLinkProvider implements ControllerLinkProvider {
    /**
     * @param id
     * @return
     */
    @Override
    public Link getSelfLink(Long id) {
        try {
            return linkTo(methodOn(CustomerController.class).getCustomer(id)).withSelfRel();
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public Link getAllLinks() {
        return linkTo(methodOn(CustomerController.class).getCustomers()).withRel("orders");
    }
}
