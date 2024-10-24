package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.PedidoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.PedidoResponse;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class PedidoMapper {
    public PedidoResponse toPedidoResponse(Pedido pedido) {
        return new PedidoResponse(
                Math.toIntExact(pedido.getIdPedido()),
                pedido.getMetodoPago(),
                pedido.getTotalMonto(),
                pedido.getEstado()
        );
    }

    public Pedido toPedido(PedidoRequest pedidoRequest, Usuario usuario) {
        return Pedido.builder()
                .metodoPago(pedidoRequest.getMetodoPago())
                .totalMonto(pedidoRequest.getTotalMonto())
                .usuario(usuario)
                .build();
    }
}
