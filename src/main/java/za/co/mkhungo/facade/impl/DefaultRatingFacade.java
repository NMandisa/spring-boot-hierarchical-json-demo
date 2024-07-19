package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.facade.RatingFacade;
import za.co.mkhungo.model.Customer;
import za.co.mkhungo.model.Rating;
import za.co.mkhungo.repository.RatingRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.ArrayList;
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
     * @return
     */
    @Override
    public List<RatingDTO> getAllRatings() {
        List<RatingDTO> ratingDTOS=new ArrayList<>();
        List<Rating> ratings= ratingRepository.findAll();
        ratings.forEach(rating -> {
            log.debug("Rating : {} ", rating);
            RatingDTO ratingDTO = MapperUtil.convertRatingModelToDto(rating);
            ratingDTOS.add(ratingDTO);
        });
        return ratingDTOS;
    }

    /**
     * @param ratingDTO
     * @return
     */
    @Override
    public Long save(RatingDTO ratingDTO) {
        return null;
    }

    /**
     * @param ratingDTO
     * @param id
     * @return
     */
    @Override
    public int edit(RatingDTO ratingDTO, Long id) {
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
