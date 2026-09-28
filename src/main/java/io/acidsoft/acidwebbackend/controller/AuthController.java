package io.acidsoft.acidwebbackend.controller;

import io.acidsoft.acidwebbackend.dto.common.ApiResponse;
import io.acidsoft.acidwebbackend.dto.request.LoginRequest;
import io.acidsoft.acidwebbackend.dto.response.JwtResponse;
import io.acidsoft.acidwebbackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("Authentication successful", jwtResponse));
    }
}
