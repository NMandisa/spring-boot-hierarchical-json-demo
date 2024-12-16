package za.co.mkhungo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.helper.base.SubTreePopulator;
import za.co.mkhungo.response.node.CustomerTree;
import za.co.mkhungo.response.node.sub.CustomerSubTree;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class CustomerTreePopulator extends SubTreePopulator<CustomerDTO, CustomerSubTree> {
    private final OrderTreePopulator orderTreePopulator;
    private final ControllerLinkProvider linkProvider;

    @Autowired
    public CustomerTreePopulator(OrderTreePopulator orderTreePopulator,@Qualifier("customerControllerLinkProvider") ControllerLinkProvider linkProvider) {
        this.orderTreePopulator = orderTreePopulator;
        this.linkProvider = linkProvider;
    }
    /**
     * Maps a single DTO to its corresponding subtree.
     *
     * @param customerDTO input DTO
     * @return transformed subtree
     */
    @Override
    protected CustomerSubTree mapToSubTree(CustomerDTO customerDTO) {
        return CustomerSubTree.builder()
                .id(customerDTO.getId())
                .firstName(customerDTO.getFirstName())
                .surname(customerDTO.getSurname())
                .orders(orderTreePopulator.populateOrderTree(customerDTO.getOrders()))
                .build()
                .add(linkProvider.getSelfLink(customerDTO.getId()));
    }

    public CustomerTree populateCustomerTree(List<CustomerDTO> customers) {
        return CustomerTree.builder().customer(mapToSubTrees(customers)).build();
    }
}
