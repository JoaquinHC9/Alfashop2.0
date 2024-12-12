package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PedidoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PedidoResponse;
import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;


import static org.junit.Assert.assertEquals;

class PedidoMapperTest {

    private PedidoMapper pedidoMapper;

    @BeforeEach
    void setUp() {
        pedidoMapper = new PedidoMapper();
    }

    @Test
    void toPedidoResponse_ShouldMapCorrectly() {
        // Arrange
        Pedido pedido = Pedido.builder()
                .idPedido(1L)
                .metodoPago(MetodoPago.CREDIT_CARD)
                .totalMonto(new BigDecimal("150.00"))
                .estado("COMPLETADO")
                .fechaCreacion(new Date())
                .build();

        // Act
        PedidoResponse response = pedidoMapper.toPedidoResponse(pedido);

        // Assert
        assertEquals(pedido.getIdPedido(), response.getIdPedido());
        assertEquals(pedido.getMetodoPago(), response.getMetodoPago());
        assertEquals(pedido.getTotalMonto(), response.getTotalMonto());
        assertEquals(pedido.getEstado(), response.getEstado());
        assertEquals(pedido.getFechaCreacion(), response.getFechaCreacion());
    }

    @Test
    void toPedido_ShouldMapCorrectly() {
        // Arrange
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setMetodoPago(MetodoPago.CREDIT_CARD);

        Usuario usuario = Usuario.builder()
                .idUsuario(1)
                .nombre("John Doe")
                .build();

        // Act
        Pedido pedido = pedidoMapper.toPedido(pedidoRequest, usuario);

        // Assert
        assertEquals(pedidoRequest.getMetodoPago(), pedido.getMetodoPago());
        assertEquals(usuario, pedido.getUsuario());
    }
}
