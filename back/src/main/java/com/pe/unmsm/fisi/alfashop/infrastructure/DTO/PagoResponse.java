package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PagoResponse {
    private Long idPago;
    private BigDecimal monto;
    private MetodoPago metodoPago;
    private Date fechaPago;
    private Long idPedido;
}
