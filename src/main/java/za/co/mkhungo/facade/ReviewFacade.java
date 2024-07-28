package za.co.mkhungo.facade;

import za.co.mkhungo.dto.ReviewDTO;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface ReviewFacade {
    List<ReviewDTO> getAllReviews();
    ReviewDTO save(ReviewDTO review);
    ReviewDTO edit(ReviewDTO review,Long id);
    int delete(Long id);
}
