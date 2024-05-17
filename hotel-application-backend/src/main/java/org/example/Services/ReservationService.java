package org.example.Services;


import org.example.Models.*;
import org.example.Repository.HotelRepository;
import org.example.Repository.ReservationRepository;
import org.example.Repository.RoomRepository;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public ResponseEntity reservateRoom(int userID, int roomID, int hotelID) {
        User user = userRepository.findById(userID).get();
        Room room = roomRepository.findById(roomID).get();
        Hotel hotel = hotelRepository.findById(hotelID).get();
        if(room.isAvailable()) {
            room.setAvailable(false);
            return new ResponseEntity(repository.save(new Reservation(user, room, hotel, LocalDateTime.now())), HttpStatus.OK);
        }
        return new ResponseEntity(new APIErrors(HttpStatus.BAD_REQUEST, "Aceasta camera este rezervata deja!"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity getUserReservations(int userID) {
        return new ResponseEntity(repository.findByUserId(userID), HttpStatus.OK);
    }

    public ResponseEntity changeRoom(int reservationID, int roomID) {
        Reservation reservation = repository.findById(reservationID).get();
        int currentRoomID = reservation.getRoom().getId();
        if(ChronoUnit.HOURS.between(LocalDateTime.now(), reservation.getCheckInDate()) >= 2) {
            if (roomRepository.findById(roomID).get().isAvailable()) {
                reservation.setRoom(roomRepository.findById(roomID).get());
                roomRepository.findById(currentRoomID).get().setAvailable(true);
                roomRepository.findById(roomID).get().setAvailable(false);
            } else {
                return new ResponseEntity(new APIErrors(HttpStatus.BAD_REQUEST, "Aceasta camera este rezervata deja!"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(new APIErrors(HttpStatus.BAD_REQUEST, "Camera nu poate fi modificata deoarece sunt mai putin de 2 ore pana la check-in!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(repository.save(reservation), HttpStatus.OK);
    }

    public ResponseEntity cancelReservation(int reservationID) {
        Reservation reservation = repository.findById(reservationID).get();
        int roomID = reservation.getRoom().getId();
        if(ChronoUnit.HOURS.between(LocalDateTime.now(), reservation.getCheckInDate()) >= 2) {
            Room currentRoom = roomRepository.findById(roomID).get();
            currentRoom.setAvailable(true);
            roomRepository.save(currentRoom);
            repository.delete(reservation);
            return new ResponseEntity(new APIErrors(HttpStatus.OK, "Rezervarea a fost stearsa cu succes!"), HttpStatus.OK);
        }
        return new ResponseEntity(new APIErrors(HttpStatus.BAD_REQUEST, "Rezervarea nu poate fi steasa deoarece sunt mai putin de 2 ore pana la check-in!"), HttpStatus.BAD_REQUEST);
    }
}
