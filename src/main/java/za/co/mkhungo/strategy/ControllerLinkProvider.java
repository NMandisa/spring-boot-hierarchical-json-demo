package za.co.mkhungo.strategy;

import org.springframework.hateoas.Link;

/**
 * @author Noxolo.Mkhungo
 */
public interface ControllerLinkProvider {
    Link getSelfLink(Long id);
    Link getAllLinks();
}
