package za.co.mkhungo.service;

import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.response.ReviewResponse;

/**
 * @author Noxolo.Mkhungo
 */
public interface ReviewService {
    ReviewResponse getAllReviews();
    Long save(ReviewDTO reviewDTO);
    int edit(ReviewDTO reviewDTO,Long id);
    int delete(Long id);
}
