package za.co.mkhungo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.mkhungo.exception.ProductNotFoundException;
import za.co.mkhungo.response.ProductResponse;
import za.co.mkhungo.service.ProductService;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@RestController
@RequestMapping("products")
@Tag(name = "Product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @Operation(
            description = "Get endpoint for product",
            summary = "product get endpoint list products",
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
    public ResponseEntity<ProductResponse> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(
            description = "endpoint for find product by id",
            summary = "find product by id",
            responses = {
                    @ApiResponse(
                            description = "Accept",
                            responseCode = "202"
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
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return ResponseEntity.accepted().body(productService.getProductById(id));
    }
}
