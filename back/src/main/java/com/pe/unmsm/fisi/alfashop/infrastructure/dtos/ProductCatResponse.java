package com.pe.unmsm.fisi.alfashop.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCatResponse {
    Integer idProducto;
    String nombre;
    String descripcion;
    Integer stock;
    BigDecimal precio;
    String imagenUrl;
    Integer idCategoria;
    String nombreCategoria;
    String descripcionCategoria;
}
