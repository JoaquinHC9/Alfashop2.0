package com.pe.unmsm.fisi.alfashop.security.controller;

import com.pe.unmsm.fisi.alfashop.security.DTO.LoginRequest;
import com.pe.unmsm.fisi.alfashop.security.DTO.RegistroRequest;
import com.pe.unmsm.fisi.alfashop.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
@CrossOrigin("http://localhost:5173/")
public class AuthController {
    private final UsuarioService usuarioService;
    @PostMapping("/registrar")
    public ResponseEntity<String> register(@RequestBody @Valid RegistroRequest userRequest) {
        return new ResponseEntity<>(usuarioService.register(userRequest), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest userRequest) {
        return new ResponseEntity<>(usuarioService.login(userRequest), HttpStatus.OK);
    }
}
