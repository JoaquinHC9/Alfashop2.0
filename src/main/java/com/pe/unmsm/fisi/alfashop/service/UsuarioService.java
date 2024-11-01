package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.security.DTO.LoginRequest;
import com.pe.unmsm.fisi.alfashop.security.DTO.RegistroRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.UsuarioRepository;
import com.pe.unmsm.fisi.alfashop.model.Rol;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import com.pe.unmsm.fisi.alfashop.security.RolEnum;
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
    public String register(RegistroRequest request) {
        if (request==null) return null;
        boolean existe = usuarioRepository.findByEmail(request.getEmail()).isPresent();
        if (existe) {
            return "El usuario ya existe";
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
        return "usuario registrado";
    }
    public String login(LoginRequest request) {
        String email = request.getEmail();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("El usuario no existe"));
        String contra = request.getContrasena();
        Authentication authentication = this.authenticate(email, contra);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(request.getEmail());

    }
    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new RuntimeException("contrasena incorrecta");
        }
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}
