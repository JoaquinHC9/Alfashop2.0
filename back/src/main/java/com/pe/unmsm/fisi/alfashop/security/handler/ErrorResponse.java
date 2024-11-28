package com.pe.unmsm.fisi.alfashop.security.handler;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ErrorResponse(
        int statusCode,
        LocalDateTime timestamp,
        String message,
        String path
) {

}
