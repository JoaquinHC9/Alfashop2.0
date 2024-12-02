package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.repository.UsuarioRepository;
import com.pe.unmsm.fisi.alfashop.model.Rol;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import com.pe.unmsm.fisi.alfashop.security.DTO.LoginRequest;
import com.pe.unmsm.fisi.alfashop.security.DTO.RegistroRequest;
import com.pe.unmsm.fisi.alfashop.security.RolEnum;
import com.pe.unmsm.fisi.alfashop.security.exception.UsuarioRegistradoExcepcion;
import com.pe.unmsm.fisi.alfashop.security.jwt.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        RegistroRequest request = new RegistroRequest("Juan", "Perez", "juan.perez@example.com", "123456", "987654321", new Date(1990, 11, 1));

        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getContrasena())).thenReturn("encodedPassword");
        when(jwtProvider.generateToken(request.getEmail())).thenReturn("jwtToken");

        String token = usuarioService.register(request);

        assertNotNull(token);
        assertEquals("jwtToken", token);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testRegisterEmailAlreadyExists() {
        RegistroRequest request = new RegistroRequest("Juan", "Perez", "juan.perez@example.com", "123456", "987654321", new Date(1990, 11, 1));

        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Usuario()));

        assertThrows(UsuarioRegistradoExcepcion.class, () -> usuarioService.register(request));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void testLoginSuccess() {
        // Simular el request de login
        LoginRequest request = new LoginRequest("sebastian.landeo@unmsm.edu.pe", "123");

        // Cifrar la contraseña para el usuario simulado
        String encryptedPassword = passwordEncoder.encode("123");

        // Crear el usuario con la contraseña cifrada
        Usuario usuario = Usuario.builder()
                .email("sebastian.landeo@unmsm.edu.pe")
                .contrasena(encryptedPassword)
                .build();

        // Crear el mock de UserDetails
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getPassword()).thenReturn(encryptedPassword); // Asegurarse de que UserDetails devuelve la contraseña cifrada

        // Configurar el mock del repositorio para encontrar al usuario
        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(usuario));

        // Configurar el mock de UserDetailsService para cargar al usuario
        when(userDetailsService.loadUserByUsername(request.getEmail())).thenReturn(userDetails);

        // Configurar el mock del PasswordEncoder para hacer coincidir la contraseña
        when(passwordEncoder.matches(request.getContrasena(), encryptedPassword)).thenReturn(true);

        // Configurar el mock del JwtProvider para generar el token
        when(jwtProvider.generateToken(request.getEmail())).thenReturn("jwtToken");

        // Ejecutar el login
        String token = usuarioService.login(request);

        // Verificar que el token generado no es nulo y es correcto
        assertNotNull(token);
        assertEquals("jwtToken", token);
    }


    @Test
    void testLoginUserNotFound() {
        LoginRequest request = new LoginRequest("juan.perez@example.com", "123456");

        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> usuarioService.login(request));
    }

    @Test
    void testAuthenticateSuccess() {
        String username = "juan.perez@example.com";
        String password = "123456";
        UserDetails userDetails = mock(UserDetails.class);

        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(passwordEncoder.matches(password, userDetails.getPassword())).thenReturn(true);

        assertNotNull(usuarioService.authenticate(username, password));
    }

    @Test
    void testAuthenticatePasswordIncorrect() {
        String username = "juan.perez@example.com";
        String password = "wrongPassword";
        UserDetails userDetails = mock(UserDetails.class);

        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(passwordEncoder.matches(password, userDetails.getPassword())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> usuarioService.authenticate(username, password));
    }

    // Nuevo
    @Test
    void testLoginUsuarioNoRegistrado() {
        LoginRequest request = new LoginRequest("correo.inexistente@unmsm.edu.pe", "123");

        // Configuramos el mock para devolver un Optional vacío
        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        // Ejecutamos el login y esperamos que se lance una excepción
        assertThrows(UsernameNotFoundException.class, () -> usuarioService.login(request));
    }

    @Test
    void testLoginContrasenaIncorrecta() {
        LoginRequest request = new LoginRequest("sebastian.landeo@unmsm.edu.pe", "incorrecta");

        // Configuramos el mock para devolver el usuario con la contraseña cifrada
        String encryptedPassword = passwordEncoder.encode("123");
        Usuario usuario = Usuario.builder()
                .email("sebastian.landeo@unmsm.edu.pe")
                .contrasena(encryptedPassword)
                .build();

        // Mock para cargar el usuario
        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(usuario));

        // Mock para verificar la contraseña incorrecta
        when(passwordEncoder.matches(request.getContrasena(), encryptedPassword)).thenReturn(false);

        // Ejecutamos el login y esperamos que se lance una excepción
        assertThrows(RuntimeException.class, () -> usuarioService.login(request));
    }


    @Test
    void testLoginDatosNulos() {
        LoginRequest request = new LoginRequest(null, null);

        // Configuramos el mock para que no encuentre el usuario
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // Esperamos que se lance la excepción UsernameNotFoundException
        assertThrows(UsernameNotFoundException.class, () -> {
            usuarioService.login(request);
        });
    }


    @Test
    void testLoginTokenGenerado() {
        // Crear un LoginRequest con las credenciales correctas
        LoginRequest request = new LoginRequest("sebastian.landeo@unmsm.edu.pe", "123");

        // Cifrar la contraseña que debe coincidir con la almacenada
        String encryptedPassword = passwordEncoder.encode("123");

        // Crear un rol para el usuario
        Rol rol = new Rol();
        rol.setRolEnum(RolEnum.ROL_USUARIO);

        // Crear el usuario con la contraseña cifrada
        Usuario usuario = Usuario.builder()
                .email("sebastian.landeo@unmsm.edu.pe")
                .contrasena(encryptedPassword)
                .roles(Collections.singleton(rol))
                .build();

        // Crear un mock de UserDetails
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getPassword()).thenReturn(encryptedPassword);
        when(userDetails.getUsername()).thenReturn("sebastian.landeo@unmsm.edu.pe");

        // Mockear el repositorio para que devuelva el usuario cuando se consulte por el email
        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(usuario));

        // Mockear el comportamiento de UserDetailsService para devolver el mock de userDetails
        when(userDetailsService.loadUserByUsername(request.getEmail())).thenReturn(userDetails);

        // Mockear el passwordEncoder para que compare correctamente las contraseñas
        when(passwordEncoder.matches(request.getContrasena(), encryptedPassword)).thenReturn(true);

        // Mockear el JWT Provider para devolver un token
        when(jwtProvider.generateToken(request.getEmail())).thenReturn("jwtToken");

        // Ejecutar el login y verificar que el token sea generado
        String token = usuarioService.login(request);

        // Verificar que el token no es null y que es el esperado
        assertNotNull(token);
        assertEquals("jwtToken", token);
    }


    @Test
    void testLoginConRoles() {
        // Datos de entrada del LoginRequest
        LoginRequest request = new LoginRequest("sebastian.landeo@unmsm.edu.pe", "123");

        // Ciframos la contraseña correctamente
        String encryptedPassword = passwordEncoder.encode("123");

        // Crear un rol para el usuario
        Rol rol = new Rol();
        rol.setRolEnum(RolEnum.ROL_USUARIO);

        // Crear un usuario con la contraseña cifrada y el rol asignado
        Usuario usuario = Usuario.builder()
                .email("sebastian.landeo@unmsm.edu.pe")
                .contrasena(encryptedPassword)
                .roles(Collections.singleton(rol))
                .build();

        // Crear un mock de UserDetails
        UserDetails userDetails = mock(UserDetails.class);

        // Configuramos el mock para devolver la contraseña cifrada cuando se llame a getPassword()
        when(userDetails.getPassword()).thenReturn(encryptedPassword);
        when(userDetails.getUsername()).thenReturn("sebastian.landeo@unmsm.edu.pe");

        // Mock para cargar el usuario desde el repositorio
        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(usuario));

        // Mock para verificar la contraseña
        when(passwordEncoder.matches(request.getContrasena(), encryptedPassword)).thenReturn(true);

        // Configuramos el mock de UserDetailsService para devolver el UserDetails correcto
        when(userDetailsService.loadUserByUsername(request.getEmail())).thenReturn(userDetails);

        // Mock del JWT Provider
        when(jwtProvider.generateToken(request.getEmail())).thenReturn("jwtToken");

        // Ejecutamos el login
        String token = usuarioService.login(request);

        // Verificamos que el token generado no sea nulo y es correcto
        assertNotNull(token);
        assertEquals("jwtToken", token);
    }


    @Test
    void testAuthenticate() {
        String username = "sebastian.landeo@unmsm.edu.pe";
        String password = "123";
        String encryptedPassword = passwordEncoder.encode(password);

        // Crear un usuario con la contraseña cifrada
        Usuario usuario = Usuario.builder()
                .email(username)
                .contrasena(encryptedPassword)
                .build();

        // Crear un mock de UserDetails
        UserDetails userDetails = mock(UserDetails.class);

        // Configuramos el mock para devolver la contraseña cifrada cuando se llame a getPassword()
        when(userDetails.getPassword()).thenReturn(encryptedPassword);

        // Mock para cargar el usuario desde el repositorio
        when(usuarioRepository.findByEmail(username)).thenReturn(Optional.of(usuario));

        // Mock para verificar la contraseña con el passwordEncoder
        when(passwordEncoder.matches(password, encryptedPassword)).thenReturn(true);

        // Configuramos el mock de UserDetailsService para devolver el UserDetails correcto
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        // Ejecutamos la autenticación
        Authentication authentication = usuarioService.authenticate(username, password);

        // Verificamos que la autenticación fue exitosa
        assertNotNull(authentication);
        assertEquals(username, authentication.getPrincipal());
    }







}
