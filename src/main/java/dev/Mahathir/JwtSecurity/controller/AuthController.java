package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.dto.AuthenticationRequest;
import dev.Mahathir.JwtSecurity.dto.AuthenticationResponse;
import dev.Mahathir.JwtSecurity.dto.RegisterRequest;
import dev.Mahathir.JwtSecurity.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/createUser")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
