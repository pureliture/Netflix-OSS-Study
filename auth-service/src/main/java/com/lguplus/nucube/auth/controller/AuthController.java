package com.lguplus.nucube.auth.controller;

import com.lguplus.nucube.auth.dto.LoginRequest;
import com.lguplus.nucube.auth.dto.LoginResponse;
import com.lguplus.nucube.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService svc;
    public AuthController(AuthService svc) { this.svc = svc; }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) throws Exception {
        return ResponseEntity.ok(svc.login(req));
    }
}
