package za.co.mkhungo.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import za.co.mkhungo.controller.CustomerController;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<CustomerDTO, EntityModel<CustomerDTO>> {
    /**
     * Converts the given entity into a {@code D}, which extends {@link RepresentationModel}.
     *
     * @param customerDTO customer data transfer object
     * @return EntityModel
     */
    @Override
    public EntityModel<CustomerDTO> toModel(CustomerDTO customerDTO) {
        try {
            return EntityModel.of(customerDTO,
                    linkTo(methodOn(CustomerController.class).getCustomer(customerDTO.getId())).withSelfRel(),
                    linkTo(methodOn(CustomerController.class).getCustomers()).withRel("customers"));
        } catch (CustomerNotFoundException e) {
            throw new CustomerException(e.getMessage());
        }

    }
}
