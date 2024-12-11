package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoResponse;
import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import com.pe.unmsm.fisi.alfashop.model.Pago;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PagoMapperTest {

    @InjectMocks
    private PagoMapper pagoMapper;

    @Mock
    private Pedido pedidoMock;

    private PagoRequest pagoRequest;

    private Pago pago;

    @BeforeEach
    void setUp() {
        // Inicializamos los mocks
        MockitoAnnotations.openMocks(this);

        // Creamos un PagoRequest de prueba
        pagoRequest = new PagoRequest();
        pagoRequest.setIdPedido(1L);
        pagoRequest.setMetodoPago(MetodoPago.valueOf("CREDIT_CARD"));
        pagoRequest.setTotalMonto(new BigDecimal("150.00"));

        // Creamos un Pago de prueba
        pago = Pago.builder()
                .idPago(1L)
                .metodoPago(MetodoPago.valueOf("CREDIT_CARD"))
                .monto(new BigDecimal("150.00"))
                .fechaCreacion(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant())) // Conversión de LocalDateTime a Date
                .build();
    }

    @Test
    void testToPago() {
        // Act
        Pago pago1 = pagoMapper.toPago(pagoRequest, pedidoMock);

        // Assert
        assertNotNull(pago1);
        assertEquals(pagoRequest.getIdPedido(), pago1.getIdPago());
        assertEquals(pagoRequest.getMetodoPago(), pago1.getMetodoPago());
        assertEquals(pagoRequest.getTotalMonto(), pago1.getMonto());
        assertNotNull(pago1.getFechaCreacion());
        assertEquals(pedidoMock, pago1.getPedido());
    }
    @Test
    void testToPagoResponse() {
        // Act
        PagoResponse response = pagoMapper.toPagoResponse(pago);

        // Assert
        assertNotNull(response);
        assertEquals(pago.getIdPago(), response.getIdPago());
        assertEquals(pago.getMonto(), response.getMonto());
        assertEquals(pago.getMetodoPago(), response.getMetodoPago());
        assertEquals(pago.getFechaCreacion(), response.getFechaPago());
        assertEquals(pago.getIdPago(), response.getIdPago());
    }
}
