package com.pe.unmsm.fisi.alfashop.security.repository;

import com.pe.unmsm.fisi.alfashop.model.Rol;
import com.pe.unmsm.fisi.alfashop.security.RolEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolEnum(RolEnum rolEnum);
}
