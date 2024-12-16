package za.co.mkhungo.service;

import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.exception.ReviewNotFoundException;
import za.co.mkhungo.response.ReviewResponse;

/**
 * @author Noxolo.Mkhungo
 */
public interface ReviewService {
    ReviewResponse getAllReviews();
    ReviewResponse getReviewById(Long id) throws ReviewNotFoundException;
    Long save(ReviewDTO reviewDTO);
    int edit(ReviewDTO reviewDTO,Long id);
    int delete(Long id);
}
