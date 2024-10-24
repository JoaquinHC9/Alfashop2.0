package com.pe.unmsm.fisi.alfashop.controller;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.PedidoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.PedidoResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ResenaRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping("/usuario/{id-usuario}")
    public ResponseEntity<List<PedidoResponse>> getPedidosByUsuario(@PathVariable("id-usuario") Integer idUsuario) {
        return ResponseEntity.ok(pedidoService.findPedidosByUsuario(idUsuario));
    }

    @PostMapping("/crear")
    public ResponseEntity<PedidoResponse> crearPedido(
            @RequestBody PedidoRequest pedidoRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.crearPedido(pedidoRequest));
    }
}
