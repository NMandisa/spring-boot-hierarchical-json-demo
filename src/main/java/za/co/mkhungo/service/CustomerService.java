package za.co.mkhungo.service;

import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.response.CustomerResponse;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerService {
    CustomerResponse getAllCustomers();
    CustomerResponse getCustomerById(Long id) throws CustomerNotFoundException;
    Long save(CustomerDTO customerDTO);
    int edit(CustomerDTO customerDTO,Long id);
    int delete(Long id);
}
