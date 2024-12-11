package com.pe.unmsm.fisi.alfashop.infrastructure.dtos;

import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    private Integer idUsuario;
    private MetodoPago metodoPago;
    private List<ProductoCompraRequest> productos;
}
