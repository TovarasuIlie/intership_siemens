package org.example.Controllers;

import org.example.DTO.LoginDTO;
import org.example.Models.User;
import org.example.Repository.UserRepository;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("add-user")
    public ResponseEntity addUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("login-user")
    public ResponseEntity loginUser(@RequestBody LoginDTO user) {
        return userService.loginUser(user);
    }
}
