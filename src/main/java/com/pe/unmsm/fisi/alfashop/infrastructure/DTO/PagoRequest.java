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
public class PagoRequest {
    private BigDecimal totalMonto;
    private MetodoPago metodoPago;
    private Long idPedido;
}
