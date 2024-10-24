package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPedido;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    private String metodoPago;
    private BigDecimal totalMonto;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    private String estado;
}
