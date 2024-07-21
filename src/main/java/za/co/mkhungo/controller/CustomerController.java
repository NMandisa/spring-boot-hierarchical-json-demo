package za.co.mkhungo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.request.CustomerValueObject;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.service.CustomerService;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@CrossOrigin(allowedHeaders = {"*"}, origins = "*")
@RestController
@RequestMapping("customers")
@Tag(name = "Customer")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @Operation(
            description = "Get endpoint for customer",
            summary = "customer get endpoint list customers",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/")
    public ResponseEntity<CustomerResponse>getCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @Operation(
            description = "endpoint for find customer by id",
            summary = "find customer by id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404"
                    )
            }
    )
    @PostMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") Long id) throws CustomerNotFoundException
    {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Operation(
            description = "endpoint for save customer",
            summary = "save customer",
            responses = {
                    @ApiResponse(
                            description = "Accepted",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = CustomerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = CustomerException.class))
                    )
            },
            parameters = {
                    @Parameter(
                            description = "Customer Request Body",
                            schema = @Schema(implementation = CustomerValueObject.class),
                            required = true
                    )}
    )
    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerValueObject customerValueObject){
        return ResponseEntity.accepted().body(customerService.save(CustomerDTO.builder()
                        .firstName(customerValueObject.getFirstName())
                        .surname(customerValueObject.getSurname()).build()));
    }

    @Operation(
            description = "endpoint for edit customer by id",
            summary = "Edit customer by id",
            responses = {
                    @ApiResponse(
                            description = "Accepted",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404"
                    )
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(@RequestBody CustomerValueObject customerValueObject, @PathVariable("id") Long id) throws CustomerException, CustomerNotFoundException {
        return ResponseEntity.accepted().body(customerService.edit(CustomerDTO.builder()
                .firstName(customerValueObject.getFirstName())
                .surname(customerValueObject.getSurname()).build(),id));
    }

}
