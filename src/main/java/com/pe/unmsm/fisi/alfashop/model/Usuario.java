package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


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
    @OneToMany (mappedBy = "usuario",cascade = CascadeType.REMOVE)
    private List<Resena> resenas;
}
