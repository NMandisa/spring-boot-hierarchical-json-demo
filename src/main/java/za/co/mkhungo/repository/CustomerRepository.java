package za.co.mkhungo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.mkhungo.model.Customer;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerRepository extends JpaRepository<Customer,Long>{
}
