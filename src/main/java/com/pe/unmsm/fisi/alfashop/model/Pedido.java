package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    private BigDecimal totalMonto;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProducto> pedidoProductos;

}
