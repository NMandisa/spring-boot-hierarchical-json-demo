package za.co.mkhungo.service;

import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.model.Rating;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface RatingService {
    List<RatingDTO> getAllRatings();
    Long save(Rating rating);
    int edit(Rating rating,Long id);
    int delete(Long id);
}
