package com.pe.unmsm.fisi.alfashop.service;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ResenaRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.infrastructure.mapper.ResenaMapper;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.ProductoRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.ResenaRepository;
import com.pe.unmsm.fisi.alfashop.infrastructure.repository.UsuarioRepository;

import com.pe.unmsm.fisi.alfashop.model.Producto;
import com.pe.unmsm.fisi.alfashop.model.Resena;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaService {
    private final ResenaRepository resenaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final ResenaMapper resenaMapper;

    public List<ResenaResponse> findResenaByUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Usuario con el id: "+ id + " no encontrado")
                );
        List<Resena> resenaList = resenaRepository.findResenasByUsuario(usuario);
        if(resenaList.isEmpty())
            throw new EntityNotFoundException("No hay resenas asociadas al usuario con el id: " + id);

        return resenaList.stream()
                .map(resenaMapper::toResenaResponse)
                .toList();
    }
    public List<ResenaResponse> findResenaByProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Producto con el id: "+ id + " no encontrado")
                );
        List<Resena> resenaList = resenaRepository.findResenasByProducto(producto);
        if(resenaList.isEmpty())
            throw new EntityNotFoundException("No hay resenas asociadas al producto con el id: " + id);
        return resenaList.stream()
                .map(resenaMapper::toResenaResponse)
                .toList();
    }
    public ResenaResponse crearResena(ResenaRequest resenaRequest) {
        Usuario usuario = usuarioRepository.findById(resenaRequest.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("No se puede crear la resena, usuario no encontrado"));
        Producto producto = productoRepository.findById(resenaRequest.getIdProducto())
                .orElseThrow(()-> new EntityNotFoundException("No se puede crear la resena, producto no encontrado"));
        Resena resena = resenaMapper.toResena(resenaRequest, usuario, producto);
        resenaRepository.save(resena);
        return resenaMapper.toResenaResponse(resena);
    }
}
