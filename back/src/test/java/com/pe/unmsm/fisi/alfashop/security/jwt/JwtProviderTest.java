package com.pe.unmsm.fisi.alfashop.security.jwt;

import com.pe.unmsm.fisi.alfashop.infrastructure.repository.UsuarioRepository;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import io.jsonwebtoken.io.Decoders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.lang.reflect.Field;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtProviderTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks

        // Usamos reflexión para modificar las propiedades privadas
        Field privateKeyField = JwtProvider.class.getDeclaredField("privateKey");
        privateKeyField.setAccessible(true);
        privateKeyField.set(jwtProvider, "secretsecretsecretsecretsecretsecretsecretsecret");

        Field expirationTimeField = JwtProvider.class.getDeclaredField("expiration");
        expirationTimeField.setAccessible(true);
        expirationTimeField.set(jwtProvider, 3600);  // Tiempo de expiración
    }


    @Test
    void generateToken_ShouldGenerateValidToken() {
        // Arrange
        String email = "test@example.com";
        int userId = 1;  // Usuario simulado
        Usuario usuario = Usuario.builder()
                .idUsuario(userId)  // Asegúrate de que el idUsuario esté inicializado
                .email(email)
                .build();

        // Simula que el repositorio devuelve el usuario con los valores correctos
        when(usuarioRepository.findUsuarioByEmail(email)).thenReturn(usuario);

        // Act
        String token = jwtProvider.generateToken(email);

        // Verificar que el token no esté vacío
        assertNotNull(token);

        // Verificar que el token es un JWT válido (estructura esperada: header.payload.signature)
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length, "El token JWT debe tener 3 partes");

        // Verificar que el token contiene el userId en el payload (decodificando el payload del JWT)
        String payload = new String(Decoders.BASE64.decode(parts[1]));
        assertTrue(payload.contains("\"userId\":" + userId));  // Verificamos que el userId esté en el payload
    }

    @Test
    void extractEmail_ShouldExtractCorrectEmailFromToken() {
        // Arrange
        String email = "test@example.com";
        int userId = 1;  // Usuario simulado

        // Crear un usuario simulado
        Usuario usuario = Usuario.builder()
                .idUsuario(userId)  // Asegúrate de que el idUsuario esté inicializado
                .email(email)
                .build();

        // Simula la respuesta del repositorio para que devuelva el usuario simulado
        when(usuarioRepository.findUsuarioByEmail(email)).thenReturn(usuario);

        // Act
        String token = jwtProvider.generateToken(email);  // Usamos el token generado anteriormente
        String extractedEmail = jwtProvider.extractEmail(token);

        // Assert
        assertEquals(email, extractedEmail);
    }


    @Test
    void validateToken_WhenTokenIsValid_ShouldReturnTrue() {
        // Arrange
        String email = "test@example.com";
        int userId = 1;  // Usuario simulado

        // Crear un usuario simulado con los detalles adecuados
        Usuario usuario = Usuario.builder()
                .idUsuario(userId)  // Asegúrate de que el idUsuario esté inicializado
                .email(email)
                .build();

        // Simula la respuesta del repositorio para que devuelva el usuario simulado
        when(usuarioRepository.findUsuarioByEmail(email)).thenReturn(usuario);

        // Genera el token usando el email simulado
        String token = jwtProvider.generateToken(email);

        // Simula el objeto UserDetails para la validación del token
        UserDetails userDetails = User.withUsername(email).password("").authorities("ROLE_USER").build();

        // Act
        boolean isValid = jwtProvider.validateToken(token, userDetails);

        // Assert
        assertTrue(isValid);
    }


    @Test
    void validateToken_WhenTokenIsInvalid_ShouldReturnFalse() {
        // Arrange
        String email = "test@example.com";
        String invalidEmail = "other@example.com";  // Email que no coincide con el que se usa para generar el token

        // Crear un usuario simulado que se devolverá cuando el repositorio busque el email
        Usuario usuario = Usuario.builder()
                .idUsuario(1)  // Simula el ID de usuario
                .email(email)  // El email de este usuario es "test@example.com"
                .build();

        // Simula que el repositorio devuelve el usuario con el email "test@example.com"
        when(usuarioRepository.findUsuarioByEmail(email)).thenReturn(usuario);

        // Generar el token con el email "test@example.com"
        String token = jwtProvider.generateToken(email);

        // Simula el objeto UserDetails con un email diferente, "other@example.com"
        UserDetails userDetails = User.withUsername(invalidEmail).password("").authorities("ROLE_USER").build();

        // Act
        boolean isValid = jwtProvider.validateToken(token, userDetails);

        // Assert
        assertFalse(isValid);
    }

}
