package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
public class ResenaRequest {
    private Integer idUsuario;
    private Integer idProducto;
    private String comentario;
    private BigDecimal puntuacion;

    public ResenaRequest() {

    }
}
