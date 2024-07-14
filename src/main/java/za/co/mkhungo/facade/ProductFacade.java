package za.co.mkhungo.facade;

import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.model.Product;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface ProductFacade {
    List<ProductDTO> getAllProducts();
    Long save(Product product);
    int edit(Product product,Long id);
    int delete(Long id);
}
