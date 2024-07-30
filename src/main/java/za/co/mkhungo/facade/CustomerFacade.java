package za.co.mkhungo.facade;

import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerFacade {
    List<CustomerDTO> fetchAllCustomers();
    CustomerDTO fetchCustomerById(long id) throws CustomerNotFoundException;
    CustomerDTO save(CustomerDTO customer);
    CustomerDTO edit(CustomerDTO customer,Long id) throws CustomerException, CustomerNotFoundException;
    int delete(Long id) throws CustomerNotFoundException;

}
