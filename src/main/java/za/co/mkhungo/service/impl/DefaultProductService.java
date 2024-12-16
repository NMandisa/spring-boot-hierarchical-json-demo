package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.exception.ProductNotFoundException;
import za.co.mkhungo.facade.ProductFacade;
import za.co.mkhungo.helper.ProductTreePopulator;
import za.co.mkhungo.response.ProductResponse;
import za.co.mkhungo.service.ProductService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultProductService implements ProductService {
    private final ProductFacade productFacade;
    private final ProductTreePopulator productTreePopulator;
    @Autowired
    public DefaultProductService(@Qualifier("defaultProductFacade") ProductFacade productFacade,
                                 ProductTreePopulator productTreePopulator){
        this.productFacade=productFacade;
        this.productTreePopulator=productTreePopulator;
    }

    /**
     * @return ProductResponse product response
     */
    @Override
    public ProductResponse getAllProducts() {
        return ProductResponse.builder()
                .products(productTreePopulator.populateProductTree(
                                productFacade.fetchAllProducts())).build();
    }

    /**
     * @param id product id
     * @return ProductResponse product response
     * @throws ProductNotFoundException Product Not Found
     */
    @Override
    public ProductResponse getProductById(long id) throws ProductNotFoundException {
        return ProductResponse.builder()
                .products(productTreePopulator.populateProductTree(
                        List.of(productFacade.fetchProductById(id)))).build();
    }

    /**
     * @param product product dto
     * @return ProductResponse product response
     */
    @Override
    public ProductResponse save(ProductDTO product) {
        return null;
    }

    /**
     * @param product product dto
     * @param id product id
     * @return ProductResponse product response
     */
    @Override
    public ProductResponse edit(ProductDTO product, Long id) {
        return null;
    }

    /**
     * @param id product id
     * @return integer value of row affect
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
