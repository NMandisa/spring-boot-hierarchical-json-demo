package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.facade.ReviewFacade;
import za.co.mkhungo.response.ReviewResponse;
import za.co.mkhungo.service.ReviewService;

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
    public ReviewResponse getAllReviews() {
        //return reviewFacade.getAllReviews();
        return null;
    }

    /**
     * @param reviewDTO
     * @return
     */
    @Override
    public Long save(ReviewDTO reviewDTO) {
        return null;
    }

    /**
     * @param reviewDTO
     * @param id
     * @return
     */
    @Override
    public int edit(ReviewDTO reviewDTO, Long id) {
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
