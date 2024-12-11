package com.pe.unmsm.fisi.alfashop.infrastructure.dtos;

import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PagoRequest {
    private BigDecimal totalMonto;
    private MetodoPago metodoPago;
    private Long idPedido;
}
