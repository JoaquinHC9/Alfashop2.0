package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductCatResponse;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductResponse;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper {
    public ProductCatResponse toProductCatResponse(Producto producto) {
        return new ProductCatResponse(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getPrecio(),
                producto.getImagenUrl(),
                producto.getCategoria().getIdCategoria(),
                producto.getCategoria().getNombre(),
                producto.getCategoria().getDescripcion()
        );
    }
    public ProductResponse toProductResponse (Producto producto){
        return new ProductResponse(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getPrecio(),
                producto.getImagenUrl()
        );
    }
}
