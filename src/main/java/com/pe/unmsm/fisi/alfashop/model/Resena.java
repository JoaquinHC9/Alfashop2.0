package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Resena {
    @Id
    private Integer idResena;
    private String comentario;
    private BigDecimal puntuacion;
    @ManyToOne
    @JoinColumn (name="id_usuario")
    private Usuario usuario;
}
