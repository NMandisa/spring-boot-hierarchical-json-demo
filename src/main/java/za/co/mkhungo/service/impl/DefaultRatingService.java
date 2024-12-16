package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.exception.RatingNotFoundException;
import za.co.mkhungo.facade.RatingFacade;
import za.co.mkhungo.helper.RatingTreePopulator;
import za.co.mkhungo.response.RatingResponse;
import za.co.mkhungo.service.RatingService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultRatingService implements RatingService {
    private final RatingFacade ratingFacade;
    private final RatingTreePopulator ratingTreePopulator;

    public DefaultRatingService(@Qualifier("defaultRatingFacade") RatingFacade ratingFacade, RatingTreePopulator ratingTreePopulator){
        this.ratingFacade=ratingFacade;
        this.ratingTreePopulator = ratingTreePopulator;
    }

    /**
     * @return
     */
    @Override
    public RatingResponse getAllRatings() {
        return RatingResponse.builder()
                .ratings(ratingTreePopulator.populateRatingTree(
                        ratingFacade.fetchAllRatings())).build();
    }

    /**
     * @param id
     * @return
     * @throws RatingNotFoundException
     */
    @Override
    public RatingResponse getRatingById(Long id) throws RatingNotFoundException {
        return RatingResponse.builder()
                .ratings(ratingTreePopulator.populateRatingTree(
                        List.of(ratingFacade.fetchRatingById(id)))).build();
    }

    /**
     * @param rating
     * @return
     */
    @Override
    public Long save(RatingDTO rating) {
        return null;
    }

    /**
     * @param rating
     * @param id
     * @return
     */
    @Override
    public int edit(RatingDTO rating, Long id) {
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
