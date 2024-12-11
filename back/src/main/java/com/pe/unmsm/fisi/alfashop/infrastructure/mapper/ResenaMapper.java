package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ResenaRequest;
import com.pe.unmsm.fisi.alfashop.infrastructure.dtos.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.model.Producto;
import com.pe.unmsm.fisi.alfashop.model.Resena;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ResenaMapper {
    public ResenaResponse toResenaResponse(Resena resena) {
        return new ResenaResponse(
                resena.getIdResena(),
                resena.getComentario(),
                resena.getPuntuacion()
        );
    }
    public Resena toResena(ResenaRequest resenaRequest, Usuario usuario, Producto producto) {
        return Resena.builder()
            .comentario(resenaRequest.getComentario())
            .puntuacion(resenaRequest.getPuntuacion())
            .usuario(usuario)
            .producto(producto)
            .build();
    }
}
