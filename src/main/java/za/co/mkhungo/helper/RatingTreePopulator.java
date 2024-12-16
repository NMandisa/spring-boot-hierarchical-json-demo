package za.co.mkhungo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.RatingDTO;
import za.co.mkhungo.helper.base.SubTreePopulator;
import za.co.mkhungo.response.node.RatingTree;
import za.co.mkhungo.response.node.sub.RatingSubTree;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class RatingTreePopulator extends SubTreePopulator<RatingDTO, RatingSubTree> {

    private final ReviewTreePopulator reviewTreePopulator;
    private final ControllerLinkProvider linkProvider;

    @Autowired
    public RatingTreePopulator(ReviewTreePopulator reviewTreePopulator,@Qualifier("ratingControllerLinkProvider") ControllerLinkProvider linkProvider) {
        this.reviewTreePopulator = reviewTreePopulator;
        this.linkProvider = linkProvider;
    }

    /**
     * Maps a single DTO to its corresponding subtree.
     *
     * @param ratingDTO input DTO
     * @return transformed subtree
     */
    @Override
    protected RatingSubTree mapToSubTree(RatingDTO ratingDTO) {
        return RatingSubTree.builder()
                .id(ratingDTO.getId())
                .rating(ratingDTO.getRate())
                .reviews(reviewTreePopulator.populateReviewTree(ratingDTO.getReviews()))
                .build()
                .add(linkProvider.getSelfLink(ratingDTO.getId()));
    }

    public RatingTree populateRatingTree(List<RatingDTO> ratings) {
        return RatingTree.builder().rating(mapToSubTrees(ratings)).build();
    }
}
