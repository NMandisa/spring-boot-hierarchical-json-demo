package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.model.Customer;
import za.co.mkhungo.repository.CustomerRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Component
public class DefaultCustomerFacade implements CustomerFacade {
    private final CustomerRepository customerRepository;
    @Autowired
    public DefaultCustomerFacade(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    /**
     * @return
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        List<Customer> customers= customerRepository.findAll();
        customers.forEach(customer -> {
            log.info("Customer : " + customer);
            CustomerDTO customerDTO = MapperUtil.convertCustomerModelToDto(customer);
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    /**
     * @param customerDTO
     * @return
     */
    @Override
    public Long save(CustomerDTO customerDTO) {
        return null;
    }

    /**
     * @param customerDTO
     * @param id
     * @return
     */
    @Override
    public int edit(CustomerDTO customerDTO, Long id) {
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
