package za.co.mkhungo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.helper.base.SubTreePopulator;
import za.co.mkhungo.response.node.ReviewTree;
import za.co.mkhungo.response.node.sub.ReviewSubTree;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class ReviewTreePopulator extends SubTreePopulator<ReviewDTO, ReviewSubTree> {

    private final ControllerLinkProvider linkProvider;

    @Autowired
    public ReviewTreePopulator(@Qualifier("reviewControllerLinkProvider") ControllerLinkProvider linkProvider) {
        this.linkProvider = linkProvider;
    }

    /**
     * Maps a single DTO to its corresponding subtree.
     *
     * @param reviewDTO input DTO
     * @return transformed subtree
     */
    @Override
    protected ReviewSubTree mapToSubTree(ReviewDTO reviewDTO) {
        return ReviewSubTree.builder()
                .id(reviewDTO.getId())
                .tagLine(reviewDTO.getTagLine())
                .comment(reviewDTO.getComment())
                .build()
                .add(linkProvider.getSelfLink(reviewDTO.getId()));
    }

    public ReviewTree populateReviewTree(List<ReviewDTO> reviews) {
        return ReviewTree.builder().review(mapToSubTrees(reviews)).build();
    }
}
