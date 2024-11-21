package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PedidoResponse {
    private Long idPedido;
    private MetodoPago metodoPago;
    private BigDecimal totalMonto;
    private String estado;
}
