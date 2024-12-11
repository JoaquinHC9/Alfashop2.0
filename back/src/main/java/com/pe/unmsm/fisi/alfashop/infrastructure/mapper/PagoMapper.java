package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoResponse;
import com.pe.unmsm.fisi.alfashop.model.Pago;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PagoMapper {
    public Pago toPago(PagoRequest pagoRequest, Pedido pedido){
        return Pago.builder()
                .idPago(pagoRequest.getIdPedido())
                .metodoPago(pagoRequest.getMetodoPago())
                .monto(pagoRequest.getTotalMonto())
                .fechaCreacion(new Date())
                .pedido(pedido)
                .build();
    }
    public PagoResponse toPagoResponse(Pago pago){
        return new PagoResponse(
                pago.getIdPago(),
                pago.getMonto(),
                pago.getMetodoPago(),
                pago.getFechaCreacion(),
                pago.getIdPago()
        );
    }
}
