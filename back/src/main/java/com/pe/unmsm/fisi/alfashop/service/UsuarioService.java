package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.exceptions.InvalidCredentialsException;
import com.pe.unmsm.fisi.alfashop.security.dtos.LoginRequest;
import com.pe.unmsm.fisi.alfashop.security.dtos.RegistroRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.UsuarioRepository;
import com.pe.unmsm.fisi.alfashop.model.Rol;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import com.pe.unmsm.fisi.alfashop.security.RolEnum;
import com.pe.unmsm.fisi.alfashop.security.dtos.TokenResponse;
import com.pe.unmsm.fisi.alfashop.security.exception.UsuarioRegistradoExcepcion;
import com.pe.unmsm.fisi.alfashop.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;
    public TokenResponse register(RegistroRequest request) {
        if (request==null) {
            throw new IllegalArgumentException("Hubo un error al registrar el usuario");
        }
        boolean existe = usuarioRepository.findByEmail(request.getEmail()).isPresent();
        if (existe) {
            throw new UsuarioRegistradoExcepcion("El email solicitado ya existe!");
        }
        Rol rolUsario = new Rol();
        rolUsario.setRolEnum(RolEnum.ROL_USUARIO);

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .telefono(request.getTelefono())
                .fechaNacimiento(request.getFechaNacimiento())
                .roles(Collections.singleton(rolUsario))
                .build();
        usuarioRepository.save(usuario);
        return jwtProvider.generateToken(request.getEmail());
    }
    public TokenResponse login(LoginRequest request) {
        String email = request.getEmail();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
        String contra = request.getContrasena();
        Authentication authentication = this.authenticate(email, contra);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generar y devolver el token
        return jwtProvider.generateToken(email);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new InvalidCredentialsException("La contraseña es incorrecta");
        }
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}
