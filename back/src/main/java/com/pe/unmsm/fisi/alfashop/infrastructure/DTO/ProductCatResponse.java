package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
public class ProductCatResponse {
    Integer idProducto;
    String nombre;
    String descripcion;
    Integer stock;
    BigDecimal precio;
    Integer idCategoria;
    String nombreCategoria;
    String descripcionCategoria;
}
