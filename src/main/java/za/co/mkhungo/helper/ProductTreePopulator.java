package za.co.mkhungo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.helper.base.SubTreePopulator;
import za.co.mkhungo.response.node.ProductTree;
import za.co.mkhungo.response.node.sub.ProductSubTree;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class ProductTreePopulator extends SubTreePopulator<ProductDTO, ProductSubTree> {

    private final RatingTreePopulator ratingTreePopulator;
    private final ControllerLinkProvider linkProvider;

    @Autowired
    public ProductTreePopulator(RatingTreePopulator ratingTreePopulator,@Qualifier("productControllerLinkProvider") ControllerLinkProvider linkProvider) {
        this.ratingTreePopulator = ratingTreePopulator;
        this.linkProvider = linkProvider;
    }

    /**
     * Maps a single DTO to its corresponding subtree.
     *
     * @param productDTO input DTO
     * @return transformed subtree
     */
    @Override
    protected ProductSubTree mapToSubTree(ProductDTO productDTO) {
        return ProductSubTree.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .ratings(ratingTreePopulator.populateRatingTree(productDTO.getRatings()))
                .build()
                .add(linkProvider.getSelfLink(productDTO.getId()));
    }
    public ProductTree populateProductTree(List<ProductDTO> products) {
        return ProductTree.builder().product(mapToSubTrees(products)).build();
    }
}
