package org.example.Services;

import org.example.DTO.FeedbackDTO;
import org.example.Models.Feedback;
import org.example.Models.Hotel;
import org.example.Models.User;
import org.example.Repository.FeedbackRepository;
import org.example.Repository.HotelRepository;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServices {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public ResponseEntity addHotelFeedback(FeedbackDTO feedback) {
        User user = userRepository.findById(feedback.getUserID()).get();
        Hotel hotel = hotelRepository.findById(feedback.getHotelID()).get();
        return new ResponseEntity(feedbackRepository.save(new Feedback(user, hotel, feedback.getFeedback())), HttpStatus.OK);
    }

    public ResponseEntity getAllHotelFeedback(int hotelID) {
        List<Feedback> feedbackList = new ArrayList<>();
        for(Feedback f: this.feedbackRepository.findAll()) {
            if(f.getHotel().getId() == hotelID){
                feedbackList.add(f);
            }
        }
        return new ResponseEntity(feedbackList, HttpStatus.OK);
    }
}
