package za.co.mkhungo.service;

import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.response.RatingResponse;

/**
 * @author Noxolo.Mkhungo
 */
public interface RatingService {
    RatingResponse getAllRatings();
    Long save(RatingDTO ratingDTO);
    int edit(RatingDTO ratingDTO,Long id);
    int delete(Long id);
}
