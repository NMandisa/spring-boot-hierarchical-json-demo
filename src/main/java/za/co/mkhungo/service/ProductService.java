package za.co.mkhungo.service;

import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.model.Product;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface ProductService {
    List<ProductDTO> getAllProducts();
    Long save(Product product);
    int edit(Product product,Long id);
    int delete(Long id);
}
