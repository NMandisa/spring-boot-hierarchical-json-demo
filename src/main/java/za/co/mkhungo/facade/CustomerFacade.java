package za.co.mkhungo.facade;

import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.model.Customer;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerFacade {
    List<CustomerDTO> getAllCustomers();
    Long save(Customer customer);
    int edit(Customer customer,Long id);
    int delete(Long id);

}
