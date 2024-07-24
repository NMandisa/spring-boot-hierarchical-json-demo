package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.ProductDTO;
import za.co.mkhungo.facade.ProductFacade;
import za.co.mkhungo.helper.PopulateResponseHelper;
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
    private final PopulateResponseHelper populateResponseHelper;
    @Autowired
    public DefaultProductService(@Qualifier("defaultProductFacade") ProductFacade productFacade,PopulateResponseHelper populateResponseHelper){
        this.productFacade=productFacade;
        this.populateResponseHelper=populateResponseHelper;
    }

    /**
     * @return ProductResponse
     */
    @Override
    public ProductResponse getAllProducts() {
        List<ProductDTO> products= productFacade.getAllProducts();
        return populateResponseHelper.populateProductTree(products);
    }

    /**
     * @param product product dto
     * @return ProductResponse
     */
    @Override
    public ProductResponse save(ProductDTO product) {
        return null;
    }

    /**
     * @param product product dto
     * @param id product id
     * @return ProductResponse
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
