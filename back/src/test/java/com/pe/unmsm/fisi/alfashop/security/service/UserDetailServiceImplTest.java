package com.pe.unmsm.fisi.alfashop.security.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.repository.UsuarioRepository;
import com.pe.unmsm.fisi.alfashop.model.Rol;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import com.pe.unmsm.fisi.alfashop.security.RolEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserDetailServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
        // Arrange
        String username = "test@example.com";
        Usuario usuario = Usuario.builder()
                .email(username)
                .contrasena("password123")
                .roles(Set.of(
                        Rol.builder().rolEnum(RolEnum.ROL_USUARIO).build(),
                        Rol.builder().rolEnum(RolEnum.ROL_ADMIN).build()
                ))
                .build();

        when(usuarioRepository.findByEmail(username)).thenReturn(Optional.of(usuario));

        // Act
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ROL_USUARIO"))); // Verifica con el nombre correcto
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ROL_ADMIN"))); // Verifica con el nombre correcto
    }

    @Test
    void loadUserByUsername_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        String username = "nonexistent@example.com";
        when(usuarioRepository.findByEmail(username)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailService.loadUserByUsername(username)
        );
        assertEquals("Usuario no encontrado!", exception.getMessage());
    }
}
