package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.facade.ReviewFacade;
import za.co.mkhungo.model.Customer;
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
     * @return
     */
    @Override
    public List<ReviewDTO> getAllReviews() {
        List<ReviewDTO> reviewDTOS=new ArrayList<>();
        List<Review> reviews= reviewRepository.findAll();
        reviews.forEach(review -> {
            log.info("Review : " + review);
            ReviewDTO reviewDTO = MapperUtil.convertReviewModelToDto(review);
            reviewDTOS.add(reviewDTO);
        });
        return reviewDTOS;
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
