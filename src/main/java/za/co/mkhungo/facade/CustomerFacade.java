package za.co.mkhungo.facade;

import za.co.mkhungo.dto.CustomerDTO;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerFacade {
    List<CustomerDTO> getAllCustomers();
    Long save(CustomerDTO customer);
    int edit(CustomerDTO customer,Long id);
    int delete(Long id);

}
