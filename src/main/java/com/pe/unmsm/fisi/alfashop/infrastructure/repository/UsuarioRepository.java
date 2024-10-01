package com.pe.unmsm.fisi.alfashop.infrastructure.repository;

import com.pe.unmsm.fisi.alfashop.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
