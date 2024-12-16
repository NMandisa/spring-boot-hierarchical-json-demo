package za.co.mkhungo.facade;

import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.exception.RatingNotFoundException;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface RatingFacade {
    List<RatingDTO> fetchAllRatings();
    RatingDTO fetchRatingById(Long id) throws RatingNotFoundException;
    RatingDTO save(RatingDTO rating);
    RatingDTO edit(RatingDTO rating,Long id);
    int delete(Long id);
}
