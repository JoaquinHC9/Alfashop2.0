package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResena;
    private String comentario;
    private BigDecimal puntuacion;
    @ManyToOne
    @JoinColumn (name="id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
}
