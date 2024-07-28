package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.facade.ReviewFacade;
import za.co.mkhungo.model.Review;
import za.co.mkhungo.repository.ReviewRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */

@Slf4j
@Component
public class DefaultReviewFacade implements ReviewFacade {
    private final ReviewRepository reviewRepository;
    @Autowired
    public DefaultReviewFacade(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }

    /**
     * @return List ReviewDTO review data transfer objects
     */
    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .peek(review -> log.debug("Review : {}", review))
                .map(MapperUtil::convertReviewModelToDto).toList();
    }

    /**
     * @param reviewDTO
     * @return ReviewDTO
     */
    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) {
        return null;
    }

    /**
     * @param reviewDTO
     * @param id
     * @return ReviewDTO
     */
    @Override
    public ReviewDTO edit(ReviewDTO reviewDTO, Long id) {
        return null;
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
