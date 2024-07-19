package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.ProductDTO;
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
     * @return List<ProductDTO> product dto list
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOS=new ArrayList<>();
        List<Product> products= productRepository.findAll();
        products.forEach(product -> {
            log.debug("Product : {} ", product);
            ProductDTO productDTO = MapperUtil.convertProductModelToDto(product);
            productDTOS.add(productDTO);
        });
        return productDTOS;
    }

    /**
     * @param productDTO product dto
     * @return
     */
    @Override
    public Long save(ProductDTO productDTO) {
        return null;
    }

    /**
     * @param productDTO product dto
     * @param id product id
     * @return
     */
    @Override
    public int edit(ProductDTO productDTO, Long id) {
        return 0;
    }

    /**
     * @param id product id
     * @return
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
