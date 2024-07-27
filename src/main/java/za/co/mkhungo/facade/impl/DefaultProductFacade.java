package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.exception.ProductNotFoundException;
import za.co.mkhungo.facade.ProductFacade;
import za.co.mkhungo.model.Product;
import za.co.mkhungo.repository.ProductRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Component
public class DefaultProductFacade implements ProductFacade {
    private final ProductRepository productRepository;
    public DefaultProductFacade(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    /**
     * @return List product data transfer object
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .peek(product -> log.debug("Product : {} ", product))
                .map(MapperUtil::convertProductModelToDto).toList();
    }

    /**
     * @param id product id
     * @return ProductDTO product data transfer object
     * @throws ProductNotFoundException product not found exception
     */
    @Override
    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        log.debug("Product : {} class {} -->", product, ProductFacade.class);
        return MapperUtil.convertProductModelToDto(product);
    }

    /**
     * @param productDTO product data transfer object
     * @return ProductDTO product data transfer object
     */
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        return null;
    }

    /**
     * @param productDTO product data transfer object
     * @param id product id
     * @return ProductDTO product data transfer object
     */
    @Override
    public ProductDTO edit(ProductDTO productDTO, Long id) {
        return null;
    }

    /**
     * @param id product id
     * @return Integer value of row affect
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
