package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private BigDecimal precio;
    @ManyToOne
    @JoinColumn(name ="id_categoria")
    Categoria categoria;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    private List<Resena> resenas;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProducto> pedidoProductos;

}
