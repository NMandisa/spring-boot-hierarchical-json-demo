package za.co.mkhungo.strategy.impl;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import za.co.mkhungo.controller.ProductController;
import za.co.mkhungo.controller.RatingController;
import za.co.mkhungo.exception.RatingNotFoundException;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class RatingControllerLinkProvider implements ControllerLinkProvider {
    /**
     * @param id
     * @return
     */
    @Override
    public Link getSelfLink(Long id) {
        try {
            return linkTo(methodOn(RatingController.class).getRatingById(id)).withSelfRel();
        } catch (RatingNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public Link getAllLinks() {
        return linkTo(methodOn(ProductController.class).getProducts()).withRel("products");
    }
}