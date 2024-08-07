package za.co.mkhungo.facade.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.model.Customer;
import za.co.mkhungo.repository.CustomerRepository;
import za.co.mkhungo.utils.MapperUtil;

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
     * @return List<CustomerDTO>
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .peek(customer -> log.debug("Customer : {}", customer))
                .map(MapperUtil::convertCustomerModelToDto).toList();
    }

    /**
     * @param id customer id
     * @return CustomerDTO customer
     */
    @Override
    public CustomerDTO getCustomerById(long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        log.debug("Customer : {} class {} -->", customer,CustomerFacade.class);
        return MapperUtil.convertCustomerModelToDto(customer);
    }

    /**
     * @param customerDTO customer
     * @return CustomerDTO customer
     */
    @Override
    public CustomerDTO save(@NonNull CustomerDTO customerDTO) {
        return MapperUtil.convertCustomerModelToDto(customerRepository.save(MapperUtil.convertCustomerDtoToModel(customerDTO)));
    }

    /**
     * @param customerDTO customer dto
     * @param id customer id
     * @return CustomerDTO
     */
    @Override
    public CustomerDTO edit(CustomerDTO customerDTO, Long id) throws CustomerException, CustomerNotFoundException {
        Customer editCustomer = customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFirstName(customerDTO.getFirstName());
                    existingCustomer.setSurname(customerDTO.getSurname());
                    return customerRepository.save(existingCustomer);
                }).orElseThrow(CustomerNotFoundException::new);
        log.debug("Edited Customer : {} class {} -->", editCustomer, CustomerFacade.class);
        return MapperUtil.convertCustomerModelToDto(editCustomer);
    }

    /**
     * @param id customer id
     * @return integer value of row affect
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
