package za.co.mkhungo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.mkhungo.response.RatingResponse;
import za.co.mkhungo.service.RatingService;


/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Controller
@RequestMapping("ratings")
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
}
