package ru.matcha.access.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.matcha.access.api.services.JwtService;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtService jwtService;

    @PostMapping("/generate")
    public String generate(@RequestBody String email) {
        return jwtService.generate(email);
    }

    @GetMapping("/get/email")
    public String getEmail(@RequestParam String jwt) {
        return jwtService.getEmail(jwt);
    }

    @PostMapping("/validate")
    public void validate(@RequestBody String jwt) {
        jwtService.validate(jwt);
    }

    @PostMapping("/parse")
    public String parse(@RequestBody(required = false) String jwt) {
        return jwtService.parse(jwt);
    }
}
