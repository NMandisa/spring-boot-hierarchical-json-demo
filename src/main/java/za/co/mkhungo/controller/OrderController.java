package za.co.mkhungo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.mkhungo.exception.OrderNotFoundException;
import za.co.mkhungo.response.OrderResponse;
import za.co.mkhungo.service.OrderService;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@RestController
@RequestMapping("orders")
@Tag(name = "Order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @Operation(
            description = "Get endpoint for order",
            summary = "order get endpoint list orders",
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
    public ResponseEntity<OrderResponse> getOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(
            description = "endpoint for find order by id",
            summary = "find order by id",
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
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long id) throws OrderNotFoundException {
        return ResponseEntity.accepted().body(orderService.getOrderById(id));
    }
}
