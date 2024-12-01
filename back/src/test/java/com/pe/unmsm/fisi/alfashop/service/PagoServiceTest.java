package com.pe.unmsm.fisi.alfashop.service;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.PagoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.PagoResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.PagoRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.PagoMapper;
import com.pe.unmsm.fisi.alfashop.model.Pago;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import com.pe.unmsm.fisi.alfashop.service.PagoService;
import com.pe.unmsm.fisi.alfashop.model.MetodoPago;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PagoServiceTest {

    // Se asegura de que los mocks sean inicializados correctamente
    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private PagoMapper pagoMapper;

    // Se inyectan los mocks en el servicio
    @InjectMocks
    private PagoService pagoService;

    private PagoRequest pagoRequest;
    private Pedido pedido;
    private Pago pago;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);

        // Simula PagoRequest
        pagoRequest = mock(PagoRequest.class);
        when(pagoRequest.getTotalMonto()).thenReturn(new BigDecimal("150.75"));
        when(pagoRequest.getMetodoPago()).thenReturn(MetodoPago.CREDIT_CARD);
        when(pagoRequest.getIdPedido()).thenReturn(1L);

        // Simula Pedido y Pago
        pedido = mock(Pedido.class);
        pago = mock(Pago.class);
    }

    @Test
    void testCreatePago() {
        // Simula la conversión de PagoRequest a Pago
        when(pagoMapper.toPago(pagoRequest, pedido)).thenReturn(pago);
        doNothing().when(pagoRepository).save(pago);

        // Act
        PagoRequest result = pagoService.createPago(pagoRequest, pedido);

        // Assert
        assertEquals(pagoRequest, result);
        verify(pagoMapper, times(1)).toPago(pagoRequest, pedido);
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    void testFindPagosByPedidoId() {
        Integer pedidoId = 1;
        List<Pago> pagos = Arrays.asList(pago);

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
