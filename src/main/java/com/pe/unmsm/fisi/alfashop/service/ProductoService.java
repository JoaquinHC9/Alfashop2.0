package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.ProductoMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.ProductoRepository;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    public ProductResponse findById(Integer id) {
        return productoRepository.findById(id)
                .map(productoMapper::toProductoResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("Producto con el id: "+ id + " no encontrado ")
                );

    }
}
