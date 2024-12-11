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
public class ResenaRequest {
    private Integer idUsuario;
    private Integer idProducto;
    private String comentario;
    private BigDecimal puntuacion;
}
