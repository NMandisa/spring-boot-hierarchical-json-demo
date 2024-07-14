package za.co.mkhungo.service;

import za.co.mkhungo.model.Customer;
import za.co.mkhungo.response.CustomerResponse;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerService {
     CustomerResponse getAllCustomers();
    Long save(Customer customer);
    int edit(Customer customer,Long id);
    int delete(Long id);
}
