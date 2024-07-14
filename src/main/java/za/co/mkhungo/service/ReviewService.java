package za.co.mkhungo.service;

import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.model.Review;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface ReviewService {
    List<ReviewDTO> getAllReviews();
    Long save(Review review);
    int edit(Review review,Long id);
    int delete(Long id);
}
