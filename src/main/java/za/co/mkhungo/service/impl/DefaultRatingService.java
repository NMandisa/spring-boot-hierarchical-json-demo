package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.facade.RatingFacade;
import za.co.mkhungo.model.Rating;
import za.co.mkhungo.service.RatingService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultRatingService implements RatingService {
    private final RatingFacade ratingFacade;
    public DefaultRatingService(@Qualifier("defaultRatingFacade") RatingFacade ratingFacade){
        this.ratingFacade=ratingFacade;
    }

    /**
     * @return
     */
    @Override
    public List<RatingDTO> getAllRatings() {
        return ratingFacade.getAllRatings();
    }

    /**
     * @param rating
     * @return
     */
    @Override
    public Long save(Rating rating) {
        return null;
    }

    /**
     * @param rating
     * @param id
     * @return
     */
    @Override
    public int edit(Rating rating, Long id) {
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
