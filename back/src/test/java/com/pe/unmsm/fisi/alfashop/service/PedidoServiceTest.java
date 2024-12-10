package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.*;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.PedidoMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.*;
import com.pe.unmsm.fisi.alfashop.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoMapper pedidoMapper;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PedidoProductoRepository pedidoProductoRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private PagoService pagoService;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findPedidosByUsuario_UsuarioExistente_RetornaPedidos() {
        // Arrange
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);

        Pedido pedido = new Pedido();
        pedido.setIdPedido(1L);
        List<Pedido> pedidos = List.of(pedido);

        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setIdPedido(1L);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(pedidoRepository.findPedidoByUsuario(usuario)).thenReturn(pedidos);
        when(pedidoMapper.toPedidoResponse(pedido)).thenReturn(pedidoResponse);

        // Act
        List<PedidoResponse> result = pedidoService.findPedidosByUsuario(usuarioId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getIdPedido());
        verify(usuarioRepository).findById(usuarioId);
        verify(pedidoRepository).findPedidoByUsuario(usuario);
    }

    @Test
    void findPedidosByUsuario_UsuarioNoExistente_LanzaExcepcion() {
        // Arrange
        Integer usuarioId = 1;
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> pedidoService.findPedidosByUsuario(usuarioId)
        );
        assertEquals("Usuario con el id: 1 no encontrado", exception.getMessage());
    }

    @Test
    void crearPedido_ProductoSinStock_LanzaExcepcion() {
        // Arrange
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setIdUsuario(1);
        ProductoCompraRequest productoCompraRequest = new ProductoCompraRequest();
        productoCompraRequest.setIdProducto(1);
        productoCompraRequest.setCantidad(10);
        pedidoRequest.setProductos(List.of(productoCompraRequest));

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);

        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setStock(5); // Stock insuficiente

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pedidoService.crearPedido(pedidoRequest)
        );
        assertEquals("Stock insuficiente para el producto: 1", exception.getMessage());
    }

    // Nuevas
    @Test
    void findPedidosByUsuario_SinPedidosAsociados_LanzaExcepcion() {
        // Arrange
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(pedidoRepository.findPedidoByUsuario(usuario)).thenReturn(List.of());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> pedidoService.findPedidosByUsuario(usuarioId)
        );
        assertEquals("No hay pedidos asociados al usuario: 1", exception.getMessage());
    }

    @Test
    void crearPedido_UsuarioNoExistente_LanzaExcepcion() {
        // Arrange
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setIdUsuario(1);

        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> pedidoService.crearPedido(pedidoRequest)
        );
        assertEquals("Usuario no encontrado: 1", exception.getMessage());
    }

    @Test
    void crearPedido_ProductoExistenteConSuficienteStock_CreaPedidoCorrectamente() {
        // Arrange
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setIdUsuario(1);
        pedidoRequest.setMetodoPago(MetodoPago.CREDIT_CARD);

        ProductoCompraRequest productoCompraRequest = new ProductoCompraRequest();
        productoCompraRequest.setIdProducto(1);
        productoCompraRequest.setCantidad(2);
        pedidoRequest.setProductos(List.of(productoCompraRequest));

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);

        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setStock(10);
        producto.setPrecio(BigDecimal.valueOf(50));

        Pedido pedido = new Pedido();
        pedido.setIdPedido(1L);
        pedido.setMetodoPago(MetodoPago.CREDIT_CARD);
        pedido.setEstado("APROBADO");
        pedido.setTotalMonto(BigDecimal.ZERO);

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        // Act
        PedidoResponse result = pedidoService.crearPedido(pedidoRequest);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdPedido());
        assertEquals(MetodoPago.CREDIT_CARD, result.getMetodoPago());
        assertEquals("APROBADO", result.getEstado());
        verify(productoRepository).save(any(Producto.class));
        verify(pedidoProductoRepository).save(any(PedidoProducto.class));
        verify(pagoService).createPago(any(PagoRequest.class), any(Pedido.class));
    }

}
