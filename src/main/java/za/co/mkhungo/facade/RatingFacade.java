package za.co.mkhungo.facade;

import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.model.Rating;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface RatingFacade {
    List<RatingDTO> getAllRatings();
    Long save(Rating rating);
    int edit(Rating rating,Long id);
    int delete(Long id);
}
