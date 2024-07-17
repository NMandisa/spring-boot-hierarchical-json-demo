package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.helper.PopulateResponseHelper;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.service.CustomerService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultCustomerService implements CustomerService {
    private final CustomerFacade customerFacade;
    private final PopulateResponseHelper populateResponseHelper;
    @Autowired
    public DefaultCustomerService(@Qualifier("defaultCustomerFacade") CustomerFacade customerFacade,PopulateResponseHelper populateResponseHelper){
        this.customerFacade=customerFacade;
        this.populateResponseHelper=populateResponseHelper;
    }

    /**
     * @return
     */
    @Override
    public CustomerResponse getAllCustomers() {
        List<CustomerDTO> customers=customerFacade.getAllCustomers();
        return populateResponseHelper.populateCustomerTree(customers);
    }

    /**
     * @param customerDTO
     * @return
     */
    @Override
    public Long save(CustomerDTO customerDTO) {
        return 0L;
    }

    /**
     * @param customerDTO
     * @param id
     * @return
     */
    @Override
    public int edit(CustomerDTO customerDTO, Long id) {
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
