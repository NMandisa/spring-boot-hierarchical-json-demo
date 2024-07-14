package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.model.Customer;
import za.co.mkhungo.response.node.CustomerTree;
import za.co.mkhungo.response.node.OrderTree;
import za.co.mkhungo.response.node.ProductTree;
import za.co.mkhungo.response.node.sub.CustomerSubTree;
import za.co.mkhungo.response.node.sub.OrderSubTree;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.response.node.sub.ProductSubTree;
import za.co.mkhungo.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultCustomerService implements CustomerService {
    private CustomerFacade customerFacade;
    @Autowired
    public DefaultCustomerService(@Qualifier("defaultCustomerFacade") CustomerFacade customerFacade){
        this.customerFacade=customerFacade;
    }

    /**
     * @return
     */
    @Override
    public CustomerResponse getAllCustomers() {
        List<CustomerDTO> customerDTOSet=customerFacade.getAllCustomers();
        CustomerResponse customerResponse=new CustomerResponse();
        CustomerTree customerTree = new CustomerTree();
        List<CustomerSubTree> customerSubTrees = new ArrayList<>();
        customerDTOSet.forEach(customerDTO -> {
            CustomerSubTree customerSubTree = new CustomerSubTree();
            customerSubTree.setId(customerDTO.getId());
            customerSubTree.setSurname(customerDTO.getSurname());
            customerSubTree.setFirstName(customerDTO.getFirstName());
            customerSubTrees.add(customerSubTree);
            List<OrderDTO> orderDTOS = customerDTO.getOrders();
            List<OrderSubTree> orderSubTrees = new ArrayList<>();
            OrderTree orderTree = new OrderTree();
            orderDTOS.forEach(orderDTO -> {
                OrderSubTree orderSubTree = new OrderSubTree();
                orderSubTree.setId(orderDTO.getId());
                orderSubTree.setOrderStatus(orderDTO.getOrderStatus());
                orderSubTree.setPlacedOn(orderDTO.getPlacedOn());
                orderSubTrees.add(orderSubTree);
                List<ProductDTO> productDTOS = orderDTO.getProducts();
                List<ProductSubTree> productSubTrees = new ArrayList<>();
                ProductTree productTree = new ProductTree();
                productDTOS.forEach(productDTO -> {
                    ProductSubTree productSubTree = new ProductSubTree();
                    productSubTree.setId(productDTO.getId());
                    productSubTree.setName(productDTO.getName());
                    productSubTree.setPrice(productDTO.getPrice());
                    productSubTrees.add(productSubTree);
                });
                productTree.setProduct(productSubTrees);
                orderSubTree.setProducts(productTree);
            });
            orderTree.setOrder(orderSubTrees);
            customerSubTree.setOrders(orderTree);
        });
        customerTree.setCustomer(customerSubTrees);
        customerResponse.setCustomers(customerTree);
        return customerResponse;
    }

    /**
     * @param customer
     * @return
     */
    @Override
    public Long save(Customer customer) {
        return null;
    }

    /**
     * @param customer
     * @param id
     * @return
     */
    @Override
    public int edit(Customer customer, Long id) {
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
