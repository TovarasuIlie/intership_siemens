package org.example.Controllers;

import org.example.Models.Reservation;
import org.example.Repository.ReservationRepository;
import org.example.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("get-user-reservations/{id}")
    public ResponseEntity getUserReservations(@PathVariable(name = "id") int id) {
        return reservationService.getUserReservations(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("add-reservation/{userID}/{roomID}/{hotelID}")
    public ResponseEntity addReservations(@PathVariable(name = "userID") int userID, @PathVariable(name = "roomID") int roomID, @PathVariable(name = "hotelID") int hotelID) {
        return reservationService.reservateRoom(userID, roomID, hotelID);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("change-room-reservation/{reservationID}/{roomID}")
    public ResponseEntity changeRoomReservation(@PathVariable(name = "reservationID") int reservationID, @PathVariable(name = "roomID") int roomID) {
        return reservationService.changeRoom(reservationID, roomID);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("cancel-room-reservation/{reservationID}")
    public ResponseEntity cancelRoomReservation(@PathVariable(name = "reservationID") int reservationID) {
        return reservationService.cancelReservation(reservationID);
    }
}
