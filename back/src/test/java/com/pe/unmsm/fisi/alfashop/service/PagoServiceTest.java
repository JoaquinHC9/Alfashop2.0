package com.pe.unmsm.fisi.alfashop.service;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.PagoRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.PagoMapper;
import com.pe.unmsm.fisi.alfashop.model.Pago;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PagoServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(PagoServiceTest.class);
    // Se asegura de que los mocks sean inicializados correctamente
    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private PagoMapper pagoMapper;

    // Se inyectan los mocks en el servicio
    @InjectMocks
    private PagoService pagoService;

    private Pago pago;

    @BeforeEach
    void setUp() {
        // Usamos try-with-resources para asegurar el cierre de los mocks
        try (var ignored = MockitoAnnotations.openMocks(this)) {
            // Simula PagoRequest
            PagoRequest pagoRequest = mock(PagoRequest.class);
            when(pagoRequest.getTotalMonto()).thenReturn(new BigDecimal("150.75"));
            when(pagoRequest.getMetodoPago()).thenReturn(MetodoPago.CREDIT_CARD);
            when(pagoRequest.getIdPedido()).thenReturn(1L);

            // Simula Pedido y Pago
            mock(Pedido.class);
            pago = mock(Pago.class);
        } catch (Exception e) {
            logger.error("Error al inicializar los mocks", e);
        }
    }


    @Test
    void testCreatePago() {
        // Mock del Pedido
        Pedido pedidoMock = Pedido.builder()
                .idPedido(1L)
                .fechaCreacion(new Date())
                .metodoPago(MetodoPago.CREDIT_CARD)
                .totalMonto(BigDecimal.valueOf(200))
                .build();
        // Mock del PagoRequest
        PagoRequest pagoRequestMock = PagoRequest.builder()
                .idPedido(1L)
                .metodoPago(MetodoPago.CREDIT_CARD)
                .totalMonto(BigDecimal.valueOf(200))
                .build();
        // Mock del Pago
        Pago pagoMock = Pago.builder()
                .idPago(1L)
                .monto(BigDecimal.valueOf(100))
                .fechaCreacion(new Date())
                .metodoPago(MetodoPago.CREDIT_CARD)
                .pedido(new Pedido())
                .build();
        // Configurar comportamiento de los mocks
        when(pagoMapper.toPago(pagoRequestMock, pedidoMock)).thenReturn(pagoMock);
        when(pagoRepository.save(any(Pago.class))).thenReturn(pagoMock);

        // Llamar al método que se está probando
        PagoRequest result = pagoService.createPago(pagoRequestMock, pedidoMock);

        // Verificar el resultado
        assertEquals(pagoRequestMock.getIdPedido(), result.getIdPedido());
        assertEquals(pagoRequestMock.getMetodoPago(), result.getMetodoPago());
        assertEquals(pagoRequestMock.getTotalMonto(), result.getTotalMonto());
    }

    @Test
    void testFindPagosByPedidoId() {
        Integer pedidoId = 1;
        List<Pago> pagos = Collections.singletonList(pago);

        // Simula que findPagosByPedido_IdPedido retorna una lista de pagos
        when(pagoRepository.findPagosByPedido_IdPedido(pedidoId)).thenReturn(pagos);

        // Simula la conversión de Pago a PagoResponse
        PagoResponse pagoResponse = mock(PagoResponse.class);
        when(pagoMapper.toPagoResponse(pago)).thenReturn(pagoResponse);

        // Act
        List<PagoResponse> result = pagoService.findPagosByPedidoId(pedidoId);

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(pagoResponse));
        verify(pagoRepository, times(1)).findPagosByPedido_IdPedido(pedidoId);
        verify(pagoMapper, times(1)).toPagoResponse(pago);
    }
}
