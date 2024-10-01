package com.pe.unmsm.fisi.alfashop.infrastructure.repository;

import com.pe.unmsm.fisi.alfashop.model.Resena;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {
    List<Resena> findResenasByUsuario(Usuario usuario);
}
