package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ProductCatResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ProductResponse;
import com.pe.unmsm.fisi.alfashop.model.Categoria;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;

public class ProductoMapperTest {

    @InjectMocks
    private ProductoMapper productoMapper;

    @Mock
    private Categoria categoriaMock;

    private Producto producto;

    @BeforeEach
    void setUp() {
        // Inicializamos los mocks
        MockitoAnnotations.openMocks(this);

        // Creamos un Producto de prueba
        producto = Producto.builder()
                .idProducto(1)
                .nombre("Producto 1")
                .descripcion("Descripción del producto")
                .stock(10)
                .precio(new BigDecimal("100.00"))
                .imagenUrl("http://imagen.com/producto1.jpg")
                .categoria(categoriaMock)
                .build();

        // Mockeamos la categoría
        when(categoriaMock.getIdCategoria()).thenReturn(1);
        when(categoriaMock.getNombre()).thenReturn("Categoria 1");
        when(categoriaMock.getDescripcion()).thenReturn("Descripción de la categoría");
    }

    @Test
    void testToProductCatResponse() {
        // Act
        ProductCatResponse response = productoMapper.toProductCatResponse(producto);

        // Assert
        assertNotNull(response);
        assertEquals(producto.getIdProducto(), response.getIdProducto());
        assertEquals(producto.getNombre(), response.getNombre());
        assertEquals(producto.getDescripcion(), response.getDescripcion());
        assertEquals(producto.getStock(), response.getStock());
        assertEquals(producto.getPrecio(), response.getPrecio());
        assertEquals(producto.getImagenUrl(), response.getImagenUrl());
        assertEquals(categoriaMock.getIdCategoria(), response.getIdCategoria());
        assertEquals(categoriaMock.getNombre(), response.getNombreCategoria());
        assertEquals(categoriaMock.getDescripcion(), response.getDescripcionCategoria());
    }
    @Test
    void testToProductResponse() {
        // Act
        ProductResponse response = productoMapper.toProductResponse(producto);

        // Assert
        assertNotNull(response);
        assertEquals(producto.getIdProducto(), response.getIdProducto());
        assertEquals(producto.getNombre(), response.getNombre());
        assertEquals(producto.getDescripcion(), response.getDescripcion());
        assertEquals(producto.getStock(), response.getStock());
        assertEquals(producto.getPrecio(), response.getPrecio());
        assertEquals(producto.getImagenUrl(), response.getImagenUrl());
    }
}