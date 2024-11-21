package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductoCompraRequest {
    private Integer idProducto;
    private Double cantidad;
}
