package za.co.mkhungo.facade;

import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerNotFoundException;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerFacade {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(long id) throws CustomerNotFoundException;
    Long save(CustomerDTO customer);
    int edit(CustomerDTO customer,Long id);
    int delete(Long id);

}
