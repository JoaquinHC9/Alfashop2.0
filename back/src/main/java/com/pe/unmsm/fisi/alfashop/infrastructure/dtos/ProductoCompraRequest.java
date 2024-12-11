package com.pe.unmsm.fisi.alfashop.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCompraRequest {
    private Integer idProducto;
    private Integer cantidad;
}
