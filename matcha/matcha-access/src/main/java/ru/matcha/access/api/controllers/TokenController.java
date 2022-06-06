package ru.matcha.access.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matcha.access.api.services.JwtService;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtService jwtService;

    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody String email) {
        return ResponseEntity.ok(jwtService.generate(email));
    }

    @GetMapping("/get/email")
    public ResponseEntity<String> getEmail(@RequestParam String jwt) {
        return ResponseEntity.ok(jwtService.getEmail(jwt));
    }

    @PostMapping("/validate")
    public ResponseEntity<Void> validate(@RequestBody String jwt) {
        jwtService.validate(jwt);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parse(@RequestBody(required = false) String jwt) {
        return ResponseEntity.ok(jwtService.parse(jwt));
    }
}
