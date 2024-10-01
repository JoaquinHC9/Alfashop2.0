package com.pe.unmsm.fisi.alfashop.infrastructure.mapper;

import com.pe.unmsm.fisi.alfashop.infrastructure.DTO.ResenaResponse;
import com.pe.unmsm.fisi.alfashop.model.Resena;
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
}
