package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.PagoResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.PagoMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.PagoRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.PedidoRepository;
import com.pe.unmsm.fisi.alfashop.model.Pago;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagoMapper pagoMapper;
    public PagoRequest createPago(PagoRequest pagoRequest, Pedido pedido) {
        Pago pago = pagoMapper.toPago(pagoRequest,pedido);

        pagoRepository.save(pago);
        return pagoRequest;
    }

    public List<PagoResponse> findPagosByPedidoId(Integer pedidoId) {

        List<Pago> pagos = pagoRepository.findPagosByPedido_IdPedido(pedidoId);

        return pagos.stream()
                .map(pagoMapper::toPagoResponse).toList();
    }
}
