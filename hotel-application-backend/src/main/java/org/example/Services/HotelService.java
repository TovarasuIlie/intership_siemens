package org.example.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Models.APIErrors;
import org.example.Models.Hotel;
import org.example.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public ResponseEntity saveFromJSON() {
        if(hotelRepository.findAll().size() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Hotel>> typeReference = new TypeReference<List<Hotel>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/JSON/hotels.json");
            System.out.println("inputStream. = " + inputStream);
            try {
                List<Hotel> hotels = mapper.readValue(inputStream, typeReference);
                return new ResponseEntity(hotelRepository.saveAll(hotels), HttpStatus.BAD_REQUEST);
            } catch (IOException e) {
                return new ResponseEntity(new APIErrors(HttpStatus.BAD_REQUEST, "A aparut o eroare!" + e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(new APIErrors(HttpStatus.BAD_REQUEST, "Datele sunt deja introduse"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity getHotelFromArea(double latitude, double longitude, double distance) {
        List<Hotel> hotels = new ArrayList<>();
        for(Hotel h: this.hotelRepository.findAll()) {
            if(h.calculateDistance(latitude, longitude) <= distance) {
                hotels.add(h);
            }
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

    public ResponseEntity getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        for(Hotel h: this.hotelRepository.findAll()) {
            hotels.add(h);
        }
        return new ResponseEntity(hotels, HttpStatus.OK);
    }

}
