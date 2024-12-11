package com.pe.unmsm.fisi.alfashop.security.jwt;

import com.pe.unmsm.fisi.alfashop.security.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JwtFilterTest {

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private ApplicationContext context;

    @Mock
    private UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private JwtFilter jwtFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtFilter = new JwtFilter(jwtProvider, context);
    }

    @Test
    void doFilterInternal_WhenTokenIsValid_ShouldSetAuthentication() throws ServletException, IOException {
        // Arrange
        String token = "valid_token";
        String email = "test@example.com";

        // Mock request header
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        // Mock JwtProvider methods
        when(jwtProvider.extractEmail(token)).thenReturn(email);

        // Mock UserDetails from UserDetailServiceImpl
        UserDetails userDetails = User.withUsername(email).password("").authorities("ROLE_USER").build();
        when(userDetailServiceImpl.loadUserByUsername(email)).thenReturn(userDetails);
        when(jwtProvider.validateToken(token, userDetails)).thenReturn(true);

        // Mock ApplicationContext
        when(context.getBean(UserDetailServiceImpl.class)).thenReturn(userDetailServiceImpl);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(userDetails, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Test
    void doFilterInternal_WhenNoToken_ShouldNotSetAuthentication() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_WhenInvalidToken_ShouldNotSetAuthentication() throws ServletException, IOException {
        // Arrange
        String token = "invalid_token";
        String email = "test@example.com";

        // Mock request header
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtProvider.extractEmail(token)).thenReturn(email);

        // Mock UserDetailServiceImpl behavior
        UserDetails userDetails = User.withUsername(email).password("").authorities("ROLE_USER").build();
        when(userDetailServiceImpl.loadUserByUsername(email)).thenReturn(userDetails);
        when(jwtProvider.validateToken(token, userDetails)).thenReturn(false);

        // Mock ApplicationContext to return the userDetailServiceImpl
        when(context.getBean(UserDetailServiceImpl.class)).thenReturn(userDetailServiceImpl);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}

