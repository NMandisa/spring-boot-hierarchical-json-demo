package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.facade.RatingFacade;
import za.co.mkhungo.repository.RatingRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Component
public class DefaultRatingFacade implements RatingFacade {
    private final RatingRepository ratingRepository;
    @Autowired
    public DefaultRatingFacade(RatingRepository ratingRepository){
        this.ratingRepository=ratingRepository;
    }

    /**
     * @return List RatingDTO rating data transfer objects
     */
    @Override
    public List<RatingDTO> getAllRatings() {
        return ratingRepository.findAll().stream()
                .peek(rating -> log.debug("Rating : {} ", rating))
                .map(MapperUtil::convertRatingModelToDto).toList();
    }

    /**
     * @param ratingDTO rating data transfer object
     * @return RatingDTO
     */
    @Override
    public RatingDTO save(RatingDTO ratingDTO) {
        return null;
    }

    /**
     * @param ratingDTO rating data transfer object
     * @param id rating id
     * @return RatingDTO
     */
    @Override
    public RatingDTO edit(RatingDTO ratingDTO, Long id) {
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
