package ru.matcha.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.matcha.exceptions.EmailAlreadyExistsException;
import ru.matcha.models.entities.User;
import ru.matcha.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {

    private final UserService userService;

//    @GetMapping(value = "/profile")
//    public ResponseEntity<User> profile() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return ResponseEntity.ok(userService.getDetails(user));
//    }
//
//    @PostMapping(value = "/registration")
//    public User profile(@RequestBody User user) throws EmailAlreadyExistsException {
//        return userService.saveUser(user);
//    }
}
