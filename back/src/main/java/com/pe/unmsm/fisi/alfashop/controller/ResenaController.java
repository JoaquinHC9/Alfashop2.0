package com.pe.unmsm.fisi.alfashop.controller;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ResenaRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.service.ResenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/resenas")
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
    @PostMapping("/crear")
    public ResponseEntity<ResenaResponse> crearResena(
            @RequestBody ResenaRequest resenaRequest
        ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resenaService.crearResena(resenaRequest));
    }
}
