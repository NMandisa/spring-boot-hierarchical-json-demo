package za.co.mkhungo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.mkhungo.exception.ReviewNotFoundException;
import za.co.mkhungo.response.ReviewResponse;
import za.co.mkhungo.service.ReviewService;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@RestController
@RequestMapping("reviews")
@Tag(name = "Review")
public class ReviewController {
    private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }
    @GetMapping("/")
    public ResponseEntity<ReviewResponse> getReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable("id") Long id) throws ReviewNotFoundException {
        return ResponseEntity.accepted().body(reviewService.getReviewById(id));
    }
}
