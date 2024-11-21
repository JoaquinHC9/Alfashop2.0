package com.pe.unmsm.fisi.alfashop.infrastructure.DTO;

import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PedidoRequest {
    private Integer idUsuario;
    private MetodoPago metodoPago;
    private List<ProductoCompraRequest> productos;
}
