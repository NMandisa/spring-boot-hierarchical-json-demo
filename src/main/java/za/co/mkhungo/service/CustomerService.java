package za.co.mkhungo.service;

import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.response.CustomerResponse;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerService {
    CustomerResponse getAllCustomers();
    CustomerResponse getCustomerById(Long id) throws CustomerNotFoundException;
    CustomerResponse save(CustomerDTO customerDTO);
    CustomerResponse edit(CustomerDTO customerDTO,Long id)throws CustomerException,CustomerNotFoundException;
    int delete(Long id);
}
