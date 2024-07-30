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
import za.co.mkhungo.helper.PopulateResponseHelper;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.service.CustomerService;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultCustomerService implements CustomerService {
    private final CustomerFacade customerFacade;
    private final PopulateResponseHelper populateResponseHelper;
    @Autowired
    public DefaultCustomerService(@Qualifier("defaultCustomerFacade") CustomerFacade customerFacade,PopulateResponseHelper populateResponseHelper){
        this.customerFacade=customerFacade;
        this.populateResponseHelper=populateResponseHelper;
    }

    /**
     * @return CustomerResponse customer response
     */
    @Override
    public CustomerResponse getAllCustomers() {
        return populateResponseHelper.populateCustomerResponse(customerFacade.fetchAllCustomers());
    }
    /**
     * @param id customer id
     * @return CustomerResponse customer response
     */
    @Override
    public CustomerResponse getCustomerById(Long id) throws CustomerNotFoundException {
        return populateResponseHelper.populateCustomerResponse(customerFacade.fetchCustomerById(id));
    }

    /**
     * @param customerDTO customer data transfer object
     * @return CustomerResponse customer response
     */
    @SneakyThrows
    @Override
    public CustomerResponse save(CustomerDTO customerDTO) {
        //return populateResponseHelper.populateCustomerResponse(customerFacade.save(customerDTO));
        if (customerDTO == null) {
            throw new IllegalArgumentException("CustomerDTO cannot be null");
        }
        try {
            CustomerDTO savedCustomer = customerFacade.save(customerDTO);
            return populateResponseHelper.populateCustomerResponse(savedCustomer);
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
        return populateResponseHelper.populateCustomerResponse(customerFacade.edit(customerDTO,id));
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
