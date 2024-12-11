package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ResenaRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.ResenaMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.ProductoRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.ResenaRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.UsuarioRepository;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import com.pe.unmsm.fisi.alfashop.model.Resena;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
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

class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ResenaMapper resenaMapper;

    @InjectMocks
    private ResenaService resenaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findResenaByUsuario_UsuarioConResenas_RetornaListaDeResenas() {
        // Arrange
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);

        Resena resena = new Resena();
        resena.setIdResena(1);
        List<Resena> resenas = List.of(resena);

        ResenaResponse resenaResponse = new ResenaResponse();
        resenaResponse.setIdResena(1);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(resenaRepository.findResenasByUsuario(usuario)).thenReturn(resenas);
        when(resenaMapper.toResenaResponse(resena)).thenReturn(resenaResponse);

        // Act
        List<ResenaResponse> result = resenaService.findResenaByUsuario(usuarioId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getIdResena());
        verify(usuarioRepository).findById(usuarioId);
        verify(resenaRepository).findResenasByUsuario(usuario);
        verify(resenaMapper).toResenaResponse(resena);
    }

    @Test
    void findResenaByUsuario_UsuarioSinResenas_LanzaExcepcion() {
        // Arrange
        Integer usuarioId = 1;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(resenaRepository.findResenasByUsuario(usuario)).thenReturn(List.of());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> resenaService.findResenaByUsuario(usuarioId)
        );
        assertEquals("No hay resenas asociadas al usuario con el id: 1", exception.getMessage());
    }

    @Test
    void findResenaByProducto_ProductoConResenas_RetornaListaDeResenas() {
        // Arrange
        Integer productoId = 1;
        Producto producto = new Producto();
        producto.setIdProducto(productoId);

        Resena resena = new Resena();
        resena.setIdResena(1);
        List<Resena> resenas = List.of(resena);

        ResenaResponse resenaResponse = new ResenaResponse();
        resenaResponse.setIdResena(1);

        when(productoRepository.findById(productoId)).thenReturn(Optional.of(producto));
        when(resenaRepository.findResenasByProducto(producto)).thenReturn(resenas);
        when(resenaMapper.toResenaResponse(resena)).thenReturn(resenaResponse);

        // Act
        List<ResenaResponse> result = resenaService.findResenaByProducto(productoId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getIdResena());
        verify(productoRepository).findById(productoId);
        verify(resenaRepository).findResenasByProducto(producto);
        verify(resenaMapper).toResenaResponse(resena);
    }

    @Test
    void crearResena_ResenaValida_CreaYDevuelveResena() {
        // Arrange
        Integer usuarioId = 1;
        Integer productoId = 2;

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);

        Producto producto = new Producto();
        producto.setIdProducto(productoId);

        ResenaRequest resenaRequest = new ResenaRequest();
        resenaRequest.setIdUsuario(usuarioId);
        resenaRequest.setIdProducto(productoId);

        Resena resena = new Resena();
        resena.setIdResena(1);

        ResenaResponse resenaResponse = new ResenaResponse();
        resenaResponse.setIdResena(1);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(productoRepository.findById(productoId)).thenReturn(Optional.of(producto));
        when(resenaMapper.toResena(resenaRequest, usuario, producto)).thenReturn(resena);
        when(resenaRepository.save(resena)).thenReturn(resena);
        when(resenaMapper.toResenaResponse(resena)).thenReturn(resenaResponse);

        // Act
        ResenaResponse result = resenaService.crearResena(resenaRequest);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdResena());
        verify(usuarioRepository).findById(usuarioId);
        verify(productoRepository).findById(productoId);
        verify(resenaRepository).save(resena);
        verify(resenaMapper).toResenaResponse(resena);
    }

    @Test
    void crearResena_UsuarioNoExiste_LanzaExcepcion() {
        // Arrange
        Integer usuarioId = 1;
        ResenaRequest resenaRequest = new ResenaRequest();
        resenaRequest.setIdUsuario(usuarioId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> resenaService.crearResena(resenaRequest)
        );
        assertEquals("No se puede crear la resena, usuario no encontrado", exception.getMessage());
    }

    @Test
    void crearResena_ProductoNoExiste_LanzaExcepcion() {
        // Arrange
        Integer usuarioId = 1;
        Integer productoId = 2;

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);

        ResenaRequest resenaRequest = new ResenaRequest();
        resenaRequest.setIdUsuario(usuarioId);
        resenaRequest.setIdProducto(productoId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(productoRepository.findById(productoId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> resenaService.crearResena(resenaRequest)
        );
        assertEquals("No se puede crear la resena, producto no encontrado", exception.getMessage());
    }

    // Nuevas
    @Test
    void findResenaByProducto_ProductoSinResenas_LanzaExcepcion() {
        // Arrange
        Integer productoId = 2;
        Producto producto = new Producto();
        producto.setIdProducto(productoId);

        when(productoRepository.findById(productoId)).thenReturn(Optional.of(producto));
        when(resenaRepository.findResenasByProducto(producto)).thenReturn(List.of());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> resenaService.findResenaByProducto(productoId)
        );
        assertEquals("No hay resenas asociadas al producto con el id: 2", exception.getMessage());
        verify(productoRepository).findById(productoId);
        verify(resenaRepository).findResenasByProducto(producto);
    }

    @Test
    void findResenaByUsuario_UsuarioNoExiste_LanzaExcepcion() {
        // Arrange
        Integer usuarioId = 1;

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> resenaService.findResenaByUsuario(usuarioId)
        );
        assertEquals("Usuario con el id: 1 no encontrado", exception.getMessage());
        verify(usuarioRepository).findById(usuarioId);
    }

    @Test
    void findResenaByProducto_ProductoNoExiste_LanzaExcepcion() {
        // Arrange
        Integer productoId = 3;

        when(productoRepository.findById(productoId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> resenaService.findResenaByProducto(productoId)
        );
        assertEquals("Producto con el id: 3 no encontrado", exception.getMessage());
        verify(productoRepository).findById(productoId);
    }

    @Test
    void crearResena_ResenaGuardadaCorrectamente_ValidaOperacion() {
        // Arrange
        Integer usuarioId = 1;
        Integer productoId = 2;

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);

        Producto producto = new Producto();
        producto.setIdProducto(productoId);

        ResenaRequest resenaRequest = new ResenaRequest();
        resenaRequest.setIdUsuario(usuarioId);
        resenaRequest.setIdProducto(productoId);

        Resena resena = new Resena();
        resena.setIdResena(1);

        ResenaResponse resenaResponse = new ResenaResponse();
        resenaResponse.setIdResena(1);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(productoRepository.findById(productoId)).thenReturn(Optional.of(producto));
        when(resenaMapper.toResena(resenaRequest, usuario, producto)).thenReturn(resena);
        when(resenaRepository.save(resena)).thenReturn(resena);
        when(resenaMapper.toResenaResponse(resena)).thenReturn(resenaResponse);

        // Act
        ResenaResponse result = resenaService.crearResena(resenaRequest);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdResena());
        verify(usuarioRepository).findById(usuarioId);
        verify(productoRepository).findById(productoId);
        verify(resenaMapper).toResena(resenaRequest, usuario, producto);
        verify(resenaRepository).save(resena);
        verify(resenaMapper).toResenaResponse(resena);
    }

    @Test
    void crearResena_ResenaInvalida_LanzaExcepcionGenerica() {
        // Arrange
        Integer usuarioId = 1;
        Integer productoId = 2;

        ResenaRequest resenaRequest = new ResenaRequest();
        resenaRequest.setIdUsuario(usuarioId);
        resenaRequest.setIdProducto(productoId);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);

        Producto producto = new Producto();
        producto.setIdProducto(productoId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(productoRepository.findById(productoId)).thenReturn(Optional.of(producto));
        when(resenaMapper.toResena(any(), any(), any())).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(
                RuntimeException.class,
                () -> resenaService.crearResena(resenaRequest)
        );
        verify(usuarioRepository).findById(usuarioId);
        verify(productoRepository).findById(productoId);
        verify(resenaMapper).toResena(resenaRequest, usuario, producto);
    }

}
