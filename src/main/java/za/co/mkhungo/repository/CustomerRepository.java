package za.co.mkhungo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.mkhungo.model.Customer;

import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
public interface CustomerRepository extends JpaRepository<Customer,Long>{

    @Modifying
    @Query("DELETE FROM Customer c WHERE c.id = :id")
    int deleteByIdAndCount(@Param("id") Long id);

    // Search customers by first name, surname, or both
    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(c.surname) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Set<Customer> findCustomersByFilter(@Param("searchTerm") String searchTerm);

    // Search customers by first name, surname, or both, with pagination and sorting
    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(c.surname) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Customer> findCustomersByFilter(@Param("searchTerm") String searchTerm, Pageable pageable);

    // Fetch customers with pagination
    @Query("SELECT c FROM Customer c ORDER BY c.firstName ASC")
    Page<Customer> findCustomersWithPagination(Pageable pageable);

    @Query("SELECT c FROM Customer c")
    Page<Customer> findAllCustomersWithPagination(Pageable pageable);

}
