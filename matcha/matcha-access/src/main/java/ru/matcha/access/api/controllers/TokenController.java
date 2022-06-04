package ru.matcha.access.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.matcha.access.api.services.JwtService;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtService jwtService;

    @PostMapping("/generateJwt")
    public ResponseEntity<String> generateJwt(@RequestBody String email) {
        return ResponseEntity.ok(jwtService.generateJwt(email));
    }

    @PostMapping("/getEmail")
    public ResponseEntity<String> getEmail(@RequestBody String jwt) {
        return ResponseEntity.ok(jwtService.getEmail(jwt));
    }

    @PostMapping("/validateJwt")
    public ResponseEntity<Void> validateJwt(@RequestBody String jwt) {
        jwtService.validateJwt(jwt);
        return ResponseEntity.ok().build();
    }
}
