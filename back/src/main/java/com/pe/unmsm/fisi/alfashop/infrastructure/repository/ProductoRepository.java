package com.pe.unmsm.fisi.alfashop.infrastructure.repository;

import com.pe.unmsm.fisi.alfashop.model.Categoria;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findProductosByCategoria(Categoria categoria);
}
