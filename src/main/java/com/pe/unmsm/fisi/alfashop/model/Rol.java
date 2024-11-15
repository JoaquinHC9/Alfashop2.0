package com.pe.unmsm.fisi.alfashop.model;

import com.pe.unmsm.fisi.alfashop.security.RolEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;
    @Column(name="nombre",nullable = false)
    @Enumerated(EnumType.STRING)
    private RolEnum rolEnum;
}
