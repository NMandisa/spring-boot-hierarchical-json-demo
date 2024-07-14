package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.facade.ReviewFacade;
import za.co.mkhungo.model.Review;
import za.co.mkhungo.service.ReviewService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultReviewService implements ReviewService {

    private final ReviewFacade reviewFacade;

    public DefaultReviewService(@Qualifier("defaultReviewFacade")  ReviewFacade reviewFacade){
        this.reviewFacade=reviewFacade;
    }

    /**
     * @return
     */
    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewFacade.getAllReviews();
    }

    /**
     * @param review
     * @return
     */
    @Override
    public Long save(Review review) {
        return null;
    }

    /**
     * @param review
     * @param id
     * @return
     */
    @Override
    public int edit(Review review, Long id) {
        return 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
