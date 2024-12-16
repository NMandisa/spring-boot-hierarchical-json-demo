package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.exception.RatingNotFoundException;
import za.co.mkhungo.facade.RatingFacade;
import za.co.mkhungo.helper.PopulateResponseHelper;
import za.co.mkhungo.response.RatingResponse;
import za.co.mkhungo.service.RatingService;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultRatingService implements RatingService {
    private final RatingFacade ratingFacade;
    private final PopulateResponseHelper populateResponseHelper;

    public DefaultRatingService(@Qualifier("defaultRatingFacade") RatingFacade ratingFacade, PopulateResponseHelper populateResponseHelper){
        this.ratingFacade=ratingFacade;
        this.populateResponseHelper = populateResponseHelper;
    }

    /**
     * @return
     */
    @Override
    public RatingResponse getAllRatings() {
        //return ratingFacade.getAllRatings();
        return null;
    }

    /**
     * @param id
     * @return
     * @throws RatingNotFoundException
     */
    @Override
    public RatingResponse getRatingById(Long id) throws RatingNotFoundException {
        return populateResponseHelper.populateRatingResponse(ratingFacade.fetchRatingById(id));
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
