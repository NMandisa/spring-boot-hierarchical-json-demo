package za.co.mkhungo.facade;

import za.co.mkhungo.dto.ProductDTO;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface ProductFacade {
    List<ProductDTO> getAllProducts();
    ProductDTO save(ProductDTO product);
    ProductDTO edit(ProductDTO product,Long id);
    int delete(Long id);
}
