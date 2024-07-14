package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.facade.ProductFacade;
import za.co.mkhungo.model.Product;
import za.co.mkhungo.service.ProductService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultProductService implements ProductService {
    private final ProductFacade productFacade;
    @Autowired
    public DefaultProductService(@Qualifier("defaultProductFacade") ProductFacade productFacade){
        this.productFacade=productFacade;
    }

    /**
     * @return
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        return productFacade.getAllProducts();
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Long save(Product product) {
        return null;
    }

    /**
     * @param product
     * @param id
     * @return
     */
    @Override
    public int edit(Product product, Long id) {
        return 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
