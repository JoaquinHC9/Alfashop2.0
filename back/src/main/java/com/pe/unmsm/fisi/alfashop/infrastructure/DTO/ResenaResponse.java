package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ResenaResponse {

    private Integer idResena;
    private String comentario;
    private BigDecimal puntuacion;

    public ResenaResponse() {

    }
}
