package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductCatResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.ProductoMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.CategoriaRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.ProductoRepository;
import com.pe.unmsm.fisi.alfashop.model.Categoria;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProductoMapper productoMapper;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ProductosExistentes_RetornaListaDeProductos() {
        // Arrange
        Producto producto = new Producto();
        producto.setIdProducto(1);
        List<Producto> productos = List.of(producto);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setIdProducto(1);

        when(productoRepository.findAll()).thenReturn(productos);
        when(productoMapper.toProductResponse(producto)).thenReturn(productResponse);

        // Act
        List<ProductResponse> result = productoService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getIdProducto());
        verify(productoRepository).findAll();
        verify(productoMapper).toProductResponse(producto);
    }

    @Test
    void findById_ProductoExistente_RetornaProducto() {
        // Arrange
        Integer productoId = 1;
        Producto producto = new Producto();
        producto.setIdProducto(productoId);

        ProductCatResponse productCatResponse = new ProductCatResponse();
        productCatResponse.setIdProducto(productoId);

        when(productoRepository.findById(productoId)).thenReturn(Optional.of(producto));
        when(productoMapper.toProductCatResponse(producto)).thenReturn(productCatResponse);

        // Act
        ProductCatResponse result = productoService.findById(productoId);

        // Assert
        assertNotNull(result);
        assertEquals(productoId, result.getIdProducto());
        verify(productoRepository).findById(productoId);
        verify(productoMapper).toProductCatResponse(producto);
    }

    @Test
    void findById_ProductoNoExistente_LanzaExcepcion() {
        // Arrange
        Integer productoId = 1;
        when(productoRepository.findById(productoId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> productoService.findById(productoId)
        );
        assertEquals("Producto con el id: 1 no encontrado ", exception.getMessage());
    }

    @Test
    void findProductosByCategoria_CategoriaConProductos_RetornaListaDeProductos() {
        // Arrange
        Integer categoriaId = 1;
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaId);

        Producto producto = new Producto();
        producto.setIdProducto(1);
        List<Producto> productos = List.of(producto);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setIdProducto(1);

        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoria));
        when(productoRepository.findProductosByCategoria(categoria)).thenReturn(productos);
        when(productoMapper.toProductResponse(producto)).thenReturn(productResponse);

        // Act
        List<ProductResponse> result = productoService.findProductosByCategoria(categoriaId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getIdProducto());
        verify(categoriaRepository).findById(categoriaId);
        verify(productoRepository).findProductosByCategoria(categoria);
        verify(productoMapper).toProductResponse(producto);
    }

    @Test
    void findProductosByCategoria_CategoriaSinProductos_LanzaExcepcion() {
        // Arrange
        Integer categoriaId = 1;
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaId);

        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoria));
        when(productoRepository.findProductosByCategoria(categoria)).thenReturn(List.of());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> productoService.findProductosByCategoria(categoriaId)
        );
        assertEquals("No hay productos asociados a la categoría con el id: 1", exception.getMessage());
    }

    @Test
    void findProductosByCategoria_CategoriaNoExistente_LanzaExcepcion() {
        // Arrange
        Integer categoriaId = 1;
        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> productoService.findProductosByCategoria(categoriaId)
        );
        assertEquals("Categoria con el id: 1 no encontrado ", exception.getMessage());
    }
}
