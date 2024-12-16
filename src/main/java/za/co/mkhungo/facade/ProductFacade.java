package za.co.mkhungo.facade;

import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.exception.ProductException;
import za.co.mkhungo.exception.ProductNotFoundException;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface ProductFacade {
    List<ProductDTO> fetchAllProducts();
    ProductDTO fetchProductById(Long id) throws ProductNotFoundException;
    ProductDTO save(ProductDTO product);
    ProductDTO edit(ProductDTO product,Long id) throws ProductNotFoundException, ProductException;
    int delete(Long id);
}
