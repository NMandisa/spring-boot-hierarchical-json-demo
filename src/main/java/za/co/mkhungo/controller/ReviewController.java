package za.co.mkhungo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.mkhungo.dto.ReviewDTO;
import za.co.mkhungo.service.ReviewService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Controller
@RequestMapping("reviews")
public class ReviewController {
    private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }
    @GetMapping("/")
    public ResponseEntity<List<ReviewDTO>> getReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }
}
