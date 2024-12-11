package com.pe.unmsm.fisi.alfashop.infrastructure.dtos;

import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PedidoResponse {
    private Long idPedido;
    private MetodoPago metodoPago;
    private BigDecimal totalMonto;
    private String estado;
    private Date fechaCreacion;
}
