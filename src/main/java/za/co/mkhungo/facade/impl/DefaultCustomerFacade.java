package za.co.mkhungo.facade.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.model.Customer;
import za.co.mkhungo.repository.CustomerRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<CustomerDTO> fetchAllCustomers() {
        try {
            return customerRepository.findAll().stream()
                    //.peek(customer -> log.debug("Customer ID : {}", customer.getId()))
                    .filter(Objects::nonNull).map(customer -> {
                        log.debug("Customer ID : {}", customer.getId());
                        return MapperUtil.convertCustomerModelToDto(customer);
                    }).toList();
        }
        catch (Exception e) {
            log.error("Error fetching customers : {}",e.getMessage());
            throw new CustomerException(e.getMessage(),e);
        }
    }

    /**
     * @param id customer id
     * @return CustomerDTO customer
     */
    @Override
    public CustomerDTO fetchCustomerById(long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID " + id));
        log.debug("Fetching Customer with ID : {} class {} -->", id,customer);
        return MapperUtil.convertCustomerModelToDto(customer);
    }

    /**
     * @param customerDTO customer
     * @return CustomerDTO savedCustomer
     */
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        validateCustomerDTO(customerDTO);
        log.debug("Saving Customer : {}", customerDTO);
        Customer savedCustomer = customerRepository.save(MapperUtil.convertCustomerDtoToModel(customerDTO));
        return MapperUtil.convertCustomerModelToDto(savedCustomer);
    }

    /**
     * @param customerDTO customer dto
     * @param id customer id
     * @return CustomerDTO
     */
    @Transactional
    @Override
    public CustomerDTO edit(CustomerDTO customerDTO, Long id) throws CustomerException, CustomerNotFoundException {
        validateCustomerDTO(customerDTO);
        Customer editedCustomer = customerRepository.findById(id)
                .map(existingCustomer -> updateCustomerDetails(existingCustomer,customerDTO))
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID " + id));
        log.debug("Edited Customer with ID  : {}. New details  {} -->", id, editedCustomer);
        return MapperUtil.convertCustomerModelToDto(editedCustomer);
    }

    /**
     * @param id customer id
     * @return integer value of row affect
     */
    @Transactional
    @Override
    public int delete(Long id) throws CustomerNotFoundException {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found with ID" + id);
        }
        log.debug("Deleting Customer with ID {} -->", id);
        //Affected Row should return 1
        return customerRepository.deleteByIdAndCount(id);
    }

    /** Validates the CustomerDTO object. *
     * @param customerDTO the CustomerDTO to validate
     * @throws CustomerException if customerDTO is null
     */
    private void validateCustomerDTO(CustomerDTO customerDTO) {
        if(Objects.isNull(customerDTO)) {
            throw new CustomerException("CustomerDTO cannot be null");
        }
    }

    private Customer updateCustomerDetails(Customer existingCustomer, CustomerDTO customerDTO) {
        existingCustomer.setFirstName(customerDTO.getFirstName());
        existingCustomer.setSurname(customerDTO.getSurname());
        return customerRepository.save(existingCustomer);
    }
}
