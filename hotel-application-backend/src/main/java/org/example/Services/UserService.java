package org.example.Services;

import org.example.DTO.LoginDTO;
import org.example.Models.APIErrors;
import org.example.Models.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity addNewUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).size() == 0) {
            return new ResponseEntity(userRepository.save(user), HttpStatus.OK);
        }
        return new ResponseEntity(new APIErrors(HttpStatus.BAD_REQUEST, "Acest email este folosit deja"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity loginUser(LoginDTO user) {
        if(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).size() == 0) {
            return new ResponseEntity(new APIErrors(HttpStatus.NOT_FOUND, "Username-ul sau parola sunt gresite"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()), HttpStatus.OK);
        }
    }
}
