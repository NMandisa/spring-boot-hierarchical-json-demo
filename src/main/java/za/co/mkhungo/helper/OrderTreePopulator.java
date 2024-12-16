package za.co.mkhungo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.helper.base.SubTreePopulator;
import za.co.mkhungo.response.node.OrderTree;
import za.co.mkhungo.response.node.sub.OrderSubTree;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class OrderTreePopulator extends SubTreePopulator<OrderDTO, OrderSubTree> {
    private final ProductTreePopulator productTreePopulator;
    private final ControllerLinkProvider linkProvider;

    @Autowired
    public OrderTreePopulator(ProductTreePopulator productTreePopulator,@Qualifier("orderControllerLinkProvider") ControllerLinkProvider linkProvider) {
        this.productTreePopulator = productTreePopulator;
        this.linkProvider = linkProvider;
    }
    /**
     * Maps a single DTO to its corresponding subtree.
     *
     * @param orderDTO input DTO
     * @return transformed subtree
     */
    @Override
    protected OrderSubTree mapToSubTree(OrderDTO orderDTO) {
        return OrderSubTree.builder()
                .id(orderDTO.getId())
                .orderStatus(orderDTO.getOrderStatus())
                .placedOn(orderDTO.getPlacedOn())
                .products(productTreePopulator.populateProductTree(orderDTO.getProducts()))
                .build()
                .add(linkProvider.getSelfLink(orderDTO.getId()));
    }

    public OrderTree populateOrderTree(List<OrderDTO> orders) {
        return OrderTree.builder()
                .order(mapToSubTrees(orders))
                .build();
    }
}
