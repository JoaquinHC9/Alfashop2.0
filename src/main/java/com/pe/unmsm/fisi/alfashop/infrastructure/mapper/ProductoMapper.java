package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductResponse;

import com.pe.unmsm.fisi.alfashop.model.Producto;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper {
    public ProductResponse toProductoResponse(Producto producto) {
        return new ProductResponse(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getPrecio(),
                producto.getCategoria().getIdCategoria(),
                producto.getCategoria().getNombre(),
                producto.getCategoria().getDescripcion()
        );

    }
}
