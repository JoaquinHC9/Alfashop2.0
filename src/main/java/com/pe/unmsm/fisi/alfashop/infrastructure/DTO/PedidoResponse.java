package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PedidoResponse {
    private Integer idPedido;
    private String metodoPago;
    private BigDecimal totalMonto;
    private String estado;
}
