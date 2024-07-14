package za.co.mkhungo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.mkhungo.model.Rating;

/**
 * @author Noxolo.Mkhungo
 */
public interface RatingRepository extends JpaRepository<Rating,Long> {
}
