package com.pe.unmsm.fisi.alfashop.controller;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductCatResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ProductResponse;
import com.pe.unmsm.fisi.alfashop.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-productos/v1")
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping("/producto/{id-producto}")
    public ResponseEntity<ProductCatResponse> getProducto(
        @PathVariable ("id-producto") Integer idProducto
        ) {
        return ResponseEntity.ok(productoService.findById(idProducto));
    }
    @GetMapping("/categoria/{id-categoria}")
    public ResponseEntity<List<ProductResponse>> getProductosByCategoria(
            @PathVariable ("id-categoria") Integer idCategoria
    ) {
        return ResponseEntity.ok(productoService.findProductosByCategoria(idCategoria));
    }
}
