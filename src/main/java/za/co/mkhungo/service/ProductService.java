package za.co.mkhungo.service;

import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.exception.ProductNotFoundException;
import za.co.mkhungo.response.ProductResponse;

/**
 * @author Noxolo.Mkhungo
 */
public interface ProductService {
    ProductResponse getAllProducts();
    ProductResponse getProductById(long id) throws ProductNotFoundException;
    ProductResponse save(ProductDTO productDTO);
    ProductResponse edit(ProductDTO productDTO,Long id);
    int delete(Long id);
}
