package za.co.mkhungo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.mkhungo.model.Customer;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerRepository extends JpaRepository<Customer,Long>{

    @Modifying
    @Query("DELETE FROM Customer c WHERE c.id = :id")
    int deleteByIdAndCount(@Param("id") Long id);
}
