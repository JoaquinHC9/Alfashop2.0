package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String telefono;
    private Date fechaNacimiento;
}
