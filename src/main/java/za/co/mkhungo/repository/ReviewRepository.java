package za.co.mkhungo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.mkhungo.model.Review;

/**
 * @author Noxolo.Mkhungo
 */
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
