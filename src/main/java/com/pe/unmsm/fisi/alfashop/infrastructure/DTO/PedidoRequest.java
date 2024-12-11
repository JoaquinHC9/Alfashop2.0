package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PedidoRequest {
    private String metodoPago;
    private BigDecimal totalMonto;
    private Integer idUsuario;
    private String estado;
}
