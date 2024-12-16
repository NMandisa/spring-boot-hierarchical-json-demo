package za.co.mkhungo.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.helper.CustomerTreePopulator;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.service.CustomerService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultCustomerService implements CustomerService {
    private final CustomerFacade customerFacade;
    private final CustomerTreePopulator customerTreePopulator;
    @Autowired
    public DefaultCustomerService(@Qualifier("defaultCustomerFacade") CustomerFacade customerFacade,
                                  CustomerTreePopulator customerTreePopulator){
        this.customerFacade=customerFacade;
        this.customerTreePopulator=customerTreePopulator;
    }

    /**
     * @return CustomerResponse customer response
     */
    @Override
    public CustomerResponse getAllCustomers() {
        return CustomerResponse.builder()
                .customers(customerTreePopulator.populateCustomerTree(
                        customerFacade.fetchAllCustomers())).build();
    }
    /**
     * @param id customer id
     * @return CustomerResponse customer response
     */
    @Override
    public CustomerResponse getCustomerById(Long id) throws CustomerNotFoundException {
        return CustomerResponse.builder()
                .customers(customerTreePopulator.populateCustomerTree(
                        List.of(customerFacade.fetchCustomerById(id)))).build();
    }

    /**
     * @param customerDTO customer data transfer object
     * @return CustomerResponse customer response
     */
    @SneakyThrows
    @Override
    public CustomerResponse save(CustomerDTO customerDTO) {
        try {
            CustomerDTO savedCustomer = customerFacade.save(customerDTO);
            return CustomerResponse.builder()
                    .customers(customerTreePopulator.populateCustomerTree(
                            List.of(savedCustomer))).build();
        } catch (Exception e) {
            log.error("Error in save method: {}", e.getMessage(), e);
            throw new CustomerException("Failed to save customer", e);
        }
    }

    /**
     * @param customerDTO customer data transfer object
     * @param id customer  id
     * @return CustomerResponse customer response
     */
    @Override
    public CustomerResponse edit(CustomerDTO customerDTO, Long id) throws CustomerException, CustomerNotFoundException {
        return CustomerResponse.builder()
                .customers(customerTreePopulator.populateCustomerTree(
                        List.of(customerFacade.edit(customerDTO,id)))).build();
    }

    /**
     * @param id customer id
     * @return integer value of row affects
     */
    @Override
    public int delete(Long id) throws CustomerNotFoundException {
        return customerFacade.delete(id);
    }
}
