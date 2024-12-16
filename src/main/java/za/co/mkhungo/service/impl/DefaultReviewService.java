package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.exception.ReviewNotFoundException;
import za.co.mkhungo.facade.ReviewFacade;
import za.co.mkhungo.helper.ReviewTreePopulator;
import za.co.mkhungo.response.ReviewResponse;
import za.co.mkhungo.service.ReviewService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultReviewService implements ReviewService {

    private final ReviewFacade reviewFacade;
    private final ReviewTreePopulator reviewTreePopulator;

    public DefaultReviewService(@Qualifier("defaultReviewFacade")  ReviewFacade reviewFacade,ReviewTreePopulator reviewTreePopulator){
        this.reviewFacade=reviewFacade;
        this.reviewTreePopulator=reviewTreePopulator;
    }

    /**
     * @return
     */
    @Override
    public ReviewResponse getAllReviews() {
        return ReviewResponse.builder().reviews(
                reviewTreePopulator.populateReviewTree(
                        reviewFacade.fetchAllReviews())).build();
    }

    /**
     * @param id
     * @return
     * @throws ReviewNotFoundException
     */
    @Override
    public ReviewResponse getReviewById(Long id) throws ReviewNotFoundException {
        return ReviewResponse.builder().reviews(
                reviewTreePopulator.populateReviewTree(List.of(
                        reviewFacade.fetchReviewById(id)))).build();
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
