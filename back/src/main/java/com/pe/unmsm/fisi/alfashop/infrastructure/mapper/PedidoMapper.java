package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PedidoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PedidoResponse;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class PedidoMapper {
    public PedidoResponse toPedidoResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getIdPedido(),
                pedido.getMetodoPago(),
                pedido.getTotalMonto(),
                pedido.getEstado(),
                pedido.getFechaCreacion()
        );
    }

    public Pedido toPedido(PedidoRequest pedidoRequest, Usuario usuario) {
        return Pedido.builder()
                .metodoPago(pedidoRequest.getMetodoPago())
                .usuario(usuario)
                .build();
    }
}
