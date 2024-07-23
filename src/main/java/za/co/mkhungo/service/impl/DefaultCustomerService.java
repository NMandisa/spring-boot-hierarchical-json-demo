package za.co.mkhungo.service.impl;

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

import java.util.List;

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
     * @return CustomerResponse
     */
    @Override
    public CustomerResponse getAllCustomers() {
        List<CustomerDTO> customers=customerFacade.getAllCustomers();
        return populateResponseHelper.populateCustomerTree(customers);
    }
    /**
     * @param id customer id
     * @return CustomerResponse
     */
    @Override
    public CustomerResponse getCustomerById(Long id) throws CustomerNotFoundException {
        CustomerDTO customer=customerFacade.getCustomerById(id);
        CustomerResponse customerResponse=populateResponseHelper.populateCustomerTree(customer);
        log.debug("Customer Response{}",customerResponse);
        return customerResponse;
    }

    /**
     * @param customerDTO customer
     * @return CustomerResponse
     */
    @Override
    public CustomerResponse save(CustomerDTO customerDTO) {
        return populateResponseHelper.populateCustomerTree(customerFacade.save(customerDTO));
    }

    /**
     * @param customerDTO customer dto
     * @param id customer  id
     * @return CustomerResponse customer response
     */
    @Override
    public CustomerResponse edit(CustomerDTO customerDTO, Long id) throws CustomerException, CustomerNotFoundException {
        return populateResponseHelper.populateCustomerTree(customerFacade.edit(customerDTO,id));
    }

    /**
     * @param id customer id
     * @return integer value of row affects
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
