package za.co.mkhungo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.service.OrderService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Controller
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDTO>> getR(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
}
