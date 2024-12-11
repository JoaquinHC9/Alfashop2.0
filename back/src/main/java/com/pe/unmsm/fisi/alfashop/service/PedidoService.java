package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.*;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.PedidoMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.*;
import com.pe.unmsm.fisi.alfashop.model.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final UsuarioRepository usuarioRepository;
    private final PedidoProductoRepository pedidoProductoRepository;
    private final ProductoRepository productoRepository;
    private final PagoService pagoService;

    public List<PedidoResponse> findPedidosByUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Usuario con el id: "+ id + " no encontrado")
                );
        List<Pedido> pedidoList = pedidoRepository.findPedidoByUsuario(usuario);
        if(pedidoList.isEmpty())
            throw new EntityNotFoundException("No hay pedidos asociados al usuario: " + id);

        return pedidoList.stream()
                .map(pedidoMapper::toPedidoResponse)
                .toList();
    }
    @Transactional
    public PedidoResponse crearPedido(PedidoRequest pedidoRequest) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(pedidoRequest.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + pedidoRequest.getIdUsuario()));

        // Crear y guardar el pedido
        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .metodoPago(pedidoRequest.getMetodoPago())
                .fechaCreacion(new Date()) // Fecha de creación del pedido
                .estado("APROBADO") // Estado inicial del pedido
                .totalMonto(BigDecimal.ZERO) // Inicializamos el monto total en 0, será actualizado más tarde
                .build();
        pedido = pedidoRepository.save(pedido);

        // Variable para acumular el monto total
        BigDecimal totalMonto = BigDecimal.ZERO;

        // Procesar productos y crear las relaciones en PedidoProducto
        for (ProductoCompraRequest productoCompraRequest : pedidoRequest.getProductos()) {
            // Verificar que el producto exista
            Producto producto = productoRepository.findById(productoCompraRequest.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + productoCompraRequest.getIdProducto()));

            // Verificar que haya suficiente stock
            if (producto.getStock() < productoCompraRequest.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getIdProducto());
            }

            // Reducir el stock del producto
            producto.setStock(producto.getStock() - productoCompraRequest.getCantidad());
            productoRepository.save(producto);

            // Calcular el precio total del producto basado en la cantidad
            BigDecimal precioTotalProducto = producto.getPrecio().multiply(BigDecimal.valueOf(productoCompraRequest.getCantidad()));

            // Crear la relación PedidoProducto
            PedidoProducto pedidoProducto = PedidoProducto.builder()
                    .pedido(pedido)
                    .producto(producto)
                    .cantidad(productoCompraRequest.getCantidad())
                    .precioTotal(precioTotalProducto)
                    .build();
            pedidoProductoRepository.save(pedidoProducto);

            // Acumular el precio total en el monto del pedido
            totalMonto = totalMonto.add(precioTotalProducto);
        }


        // Actualizar el monto total del pedido
        pedido.setTotalMonto(totalMonto);
        pedido = pedidoRepository.save(pedido);
        var nuevoPago = new PagoRequest(
                totalMonto,
                pedido.getMetodoPago(),
                pedido.getIdPedido()
        );
        pagoService.createPago(nuevoPago,pedido);

        // Construir y devolver la respuesta
        return PedidoResponse.builder()
                .idPedido(pedido.getIdPedido())
                .metodoPago(pedido.getMetodoPago())
                .totalMonto(pedido.getTotalMonto())
                .estado(pedido.getEstado())
                .build();
    }
}
