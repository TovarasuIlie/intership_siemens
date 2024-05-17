package org.example.Controllers;

import org.example.Models.Hotel;
import org.example.Repository.HotelRepository;
import org.example.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/hotels")
public class HotelController {
    @Autowired
    private HotelRepository repository;

    @Autowired
    private HotelService hotelService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("get-all-hotels")
    public ResponseEntity getAllHotels() {
        return hotelService.getAllHotels();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("insert-hotels-json")
    public ResponseEntity insertHotelsJSON() {
        return hotelService.saveFromJSON();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("get-all-area-hotels")
    public ResponseEntity getAllAreaHotels(@RequestParam("distance") double distance, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        return hotelService.getHotelFromArea(latitude, longitude, distance);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get-hotel/{id}")
    public Hotel getHotel(@PathVariable("id") int id) {
        return this.repository.findById(id).get();
    }
}
