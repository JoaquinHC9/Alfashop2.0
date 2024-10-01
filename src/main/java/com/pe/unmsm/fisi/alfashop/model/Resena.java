package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Resena {
    @Id
    private Integer idResena;
    private String Comentario;
    private float puntacion;

    private Integer idUsuario;
}
