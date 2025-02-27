package za.co.mkhungo.facade;

import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.exception.ReviewNotFoundException;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface ReviewFacade {
    List<ReviewDTO> fetchAllReviews();
    ReviewDTO fetchReviewById(Long id) throws ReviewNotFoundException;
    ReviewDTO save(ReviewDTO review);
    ReviewDTO edit(ReviewDTO review,Long id);
    int delete(Long id);
}
