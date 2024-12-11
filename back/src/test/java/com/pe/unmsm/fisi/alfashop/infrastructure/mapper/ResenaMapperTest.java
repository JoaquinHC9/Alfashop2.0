package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ResenaRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import com.pe.unmsm.fisi.alfashop.model.Resena;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResenaMapperTest {

    private ResenaMapper resenaMapper;

    @BeforeEach
    void setUp() {
        resenaMapper = new ResenaMapper();
    }

    @Test
    void toResenaResponse_ShouldMapCorrectly() {
        // Arrange
        Resena resena = Resena.builder()
                .idResena(1)
                .comentario("Excelente producto")
                .puntuacion(BigDecimal.valueOf(5))
                .build();

        // Act
        ResenaResponse response = resenaMapper.toResenaResponse(resena);

        // Assert
        assertEquals(resena.getIdResena(), response.getIdResena());
        assertEquals(resena.getComentario(), response.getComentario());
        assertEquals(resena.getPuntuacion(), response.getPuntuacion());
    }

    @Test
    void toResena_ShouldMapCorrectly() {
        // Arrange
        ResenaRequest resenaRequest = new ResenaRequest();
        resenaRequest.setComentario("Buena calidad");
        resenaRequest.setPuntuacion(BigDecimal.valueOf(4));

        Usuario usuario = Usuario.builder()
                .idUsuario(1)
                .nombre("John Doe")
                .build();

        Producto producto = Producto.builder()
                .idProducto(1)
                .nombre("Laptop")
                .build();

        // Act
        Resena resena = resenaMapper.toResena(resenaRequest, usuario, producto);

        // Assert
        assertEquals(resenaRequest.getComentario(), resena.getComentario());
        assertEquals(resenaRequest.getPuntuacion(), resena.getPuntuacion());
        assertEquals(usuario, resena.getUsuario());
        assertEquals(producto, resena.getProducto());
    }
}