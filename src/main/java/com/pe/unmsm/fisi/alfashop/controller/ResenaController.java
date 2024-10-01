package com.pe.unmsm.fisi.alfashop.controller;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.model.Resena;
import com.pe.unmsm.fisi.alfashop.service.ResenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-resenas/v1")
public class ResenaController {
    private final ResenaService resenaService;
    @GetMapping("/usuario/{id-usuario}")
    public ResponseEntity<List<ResenaResponse>> getResenasByUsuario(
        @PathVariable("id-usuario") Integer idUsuario
    ){
        return ResponseEntity.ok(resenaService.findResenaByUsuario(idUsuario));
    }
    @GetMapping("/producto/{id-producto}")
    public ResponseEntity<List<ResenaResponse>> getResenasByProducto(
            @PathVariable("id-producto") Integer idProducto
    ){
        return ResponseEntity.ok(resenaService.findResenaByProducto(idProducto));
    }
}
