package com.pe.unmsm.fisi.alfashop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
    private List<Producto> productos;
}
