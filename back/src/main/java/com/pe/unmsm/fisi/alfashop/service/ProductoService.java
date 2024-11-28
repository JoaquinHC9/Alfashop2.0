package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductCatResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.ProductoMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.CategoriaRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.ProductoRepository;
import com.pe.unmsm.fisi.alfashop.model.Categoria;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaRepository categoriaRepository;

    public List<ProductResponse> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toProductResponse)
                .collect(Collectors.toList());
    }


    public ProductCatResponse findById(Integer id) {
        return productoRepository.findById(id)
                .map(productoMapper::toProductCatResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("Producto con el id: "+ id + " no encontrado ")
                );

    }
    public List<ProductResponse> findProductosByCategoria(Integer idCategoria) {
        Categoria cat = categoriaRepository.findById(idCategoria)
                .orElseThrow(
                        () -> new EntityNotFoundException("Categoria con el id: "+ idCategoria + " no encontrado ")
                );
        List <Producto> productoList = productoRepository.findProductosByCategoria(cat);
        if(productoList.isEmpty())
            throw new EntityNotFoundException("No hay productos asociados a la categoría con el id: " + idCategoria);
        return productoList.stream()
                .map(productoMapper::toProductResponse)
                .toList();

    }
}
