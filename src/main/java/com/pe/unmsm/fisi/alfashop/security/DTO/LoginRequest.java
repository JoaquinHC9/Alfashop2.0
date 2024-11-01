package com.pe.unmsm.fisi.alfashop.security.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    @NotNull(message = "Usuario debe tener un email")
    private String email;
    @NotNull(message = "Usuario debe tener un contrasena")
    private String contrasena;
}
