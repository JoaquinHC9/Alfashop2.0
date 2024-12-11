package com.pe.unmsm.fisi.alfashop.security.config;

import com.pe.unmsm.fisi.alfashop.security.jwt.JwtFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    private SecurityConfig securityConfig;

    @Mock
    private JwtFilter jwtFilter;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);
        securityConfig = new SecurityConfig(jwtFilter);
    }

    @Test
    void testSecurityFilterChain() throws Exception {
        // Configurar el mock de HttpSecurity
        HttpSecurity httpSecurity = mock(HttpSecurity.class);

        // Configurar los métodos que se van a invocar sobre el mock
        when(httpSecurity.cors(any())).thenReturn(httpSecurity);
        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
        when(httpSecurity.authorizeHttpRequests(any())).thenReturn(httpSecurity);
        when(httpSecurity.addFilterBefore(any(), eq(UsernamePasswordAuthenticationFilter.class))).thenReturn(httpSecurity);
        when(httpSecurity.formLogin(any())).thenReturn(httpSecurity);
        when(httpSecurity.httpBasic(any())).thenReturn(httpSecurity);
        when(httpSecurity.logout(any())).thenReturn(httpSecurity);

        // Invocar el método a probar
        securityConfig.securityFilterChain(httpSecurity);

        // Verificar que los métodos hayan sido llamados
        verify(httpSecurity).cors(any());
        verify(httpSecurity).csrf(any());
        verify(httpSecurity).sessionManagement(any());
        verify(httpSecurity).authorizeHttpRequests(any());
        verify(httpSecurity).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        verify(httpSecurity).formLogin(any());
        verify(httpSecurity).httpBasic(any());
        verify(httpSecurity).logout(any());
    }



    @Test
    void testAuthenticationManager() throws Exception {
        // Llamar al método con el mock
        AuthenticationManager result = securityConfig.authenticationManager(authenticationConfiguration);

        // Verificar que no sea null
        assertNotNull(result);
    }

    @Test
    void testPasswordEncoder() {
        assertNotNull(securityConfig.passwordEncoder());
    }

    @Test
    void testCorsConfigurationSource() {
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        assertNotNull(corsConfigurationSource);
    }
}
