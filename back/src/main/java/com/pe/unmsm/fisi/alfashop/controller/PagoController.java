package com.pe.unmsm.fisi.alfashop.controller;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoResponse;
import com.pe.unmsm.fisi.alfashop.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<PagoResponse>> findPagosByPedidoId(@PathVariable Integer idPedido) {
        List<PagoResponse> pagos = pagoService.findPagosByPedidoId(idPedido);

        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }
}
