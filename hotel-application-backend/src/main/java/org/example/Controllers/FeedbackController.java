package org.example.Controllers;

import org.example.DTO.FeedbackDTO;
import org.example.Models.Feedback;
import org.example.Services.FeedbackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackServices feedbackService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("get-hotel-feedback/{id}")
    public ResponseEntity getHotelFeedback(@PathVariable(name = "id") int id) {
        return feedbackService.getAllHotelFeedback(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("add-hotel-feedback")
    public ResponseEntity<Feedback> addHotelFeedback(@RequestBody FeedbackDTO feedback) {
        return feedbackService.addHotelFeedback(feedback);
    }
}
