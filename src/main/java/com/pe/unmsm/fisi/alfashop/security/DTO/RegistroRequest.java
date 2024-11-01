package com.pe.unmsm.fisi.alfashop.security.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class RegistroRequest {
    private Integer idUsuario;
    @NotNull(message = "Usuario debe tener un nombre")
    private String nombre;
    @NotNull(message = "Usuario debe tener un apellido")
    private String apellido;
    @NotNull(message = "Usuario debe tener un email")
    private String email;
    @NotNull(message = "Usuario debe tener un contrasena")
    private String contrasena;
    @NotNull(message = "Usuario debe tener un telefono")
    private String telefono;
    @NotNull(message = "Usuario debe colocar su fecha de nacimiento")
    private Date fechaNacimiento;
}
