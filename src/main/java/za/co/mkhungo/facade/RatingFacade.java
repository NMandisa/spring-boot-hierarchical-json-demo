package za.co.mkhungo.facade;

import za.co.mkhungo.dto.RatingDTO;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface RatingFacade {
    List<RatingDTO> getAllRatings();
    Long save(RatingDTO rating);
    int edit(RatingDTO rating,Long id);
    int delete(Long id);
}
