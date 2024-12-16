package za.co.mkhungo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.mkhungo.exception.RatingNotFoundException;
import za.co.mkhungo.response.RatingResponse;
import za.co.mkhungo.service.RatingService;


/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@RestController
@RequestMapping("ratings")
@Tag(name = "Rating")
public class RatingController {
    private final RatingService ratingService;
    @Autowired
    public RatingController(RatingService ratingService){
        this.ratingService=ratingService;
    }
    @GetMapping("/")
    public ResponseEntity<RatingResponse> getRatings(){
        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<RatingResponse> getRatingById(@PathVariable("id") Long id) throws RatingNotFoundException {
        return ResponseEntity.accepted().body(ratingService.getRatingById(id));
    }
}
