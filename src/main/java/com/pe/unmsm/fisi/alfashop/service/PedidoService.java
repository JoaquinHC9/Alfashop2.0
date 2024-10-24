package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.PedidoRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.PedidoResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ResenaRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.PedidoMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.PedidoRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.UsuarioRepository;
import com.pe.unmsm.fisi.alfashop.model.Pedido;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import com.pe.unmsm.fisi.alfashop.model.Resena;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final UsuarioRepository usuarioRepository;

    public List<PedidoResponse> findPedidosByUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Usuario con el id: "+ id + " no encontrado")
                );
        List<Pedido> pedidoList = pedidoRepository.findPedidoByUsuario(usuario);
        if(pedidoList.isEmpty())
            throw new EntityNotFoundException("No hay resenas asociadas al usuario con el id: " + id);

        return pedidoList.stream()
                .map(pedidoMapper::toPedidoResponse)
                .toList();
    }

    public PedidoResponse crearPedido(PedidoRequest pedidoRequest) {
        Usuario usuario = usuarioRepository.findById(pedidoRequest.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("No se puede crear la resena, usuario no encontrado"));
        Pedido pedido = pedidoMapper.toPedido(pedidoRequest, usuario);
        pedidoRepository.save(pedido);
        return pedidoMapper.toPedidoResponse(pedido);
    }
}
