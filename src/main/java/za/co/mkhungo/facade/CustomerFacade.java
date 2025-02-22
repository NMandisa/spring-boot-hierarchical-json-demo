package za.co.mkhungo.facade;

import org.springframework.data.domain.Page;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;

import java.util.List;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerFacade {
    List<CustomerDTO> fetchAllCustomers();
    Page<CustomerDTO> fetchCustomersWithPagination(int page, int size, String sortBy, String sortDir);
    Set<CustomerDTO> fetchCustomersByFilter(String searchTerm);
    Page<CustomerDTO> fetchCustomersByFilter(String searchTerm,int page, int size, String firstName, String asc);
    Page<CustomerDTO> fetchCustomersWithPagination(int page, int size);
    CustomerDTO fetchCustomerById(long id) throws CustomerNotFoundException;
    CustomerDTO save(CustomerDTO customer);
    CustomerDTO edit(CustomerDTO customer,Long id) throws CustomerException, CustomerNotFoundException;
    int delete(Long id) throws CustomerNotFoundException;


}
