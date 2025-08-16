package com.jairoontiveros.foro_hub.domain.topico;

import java.time.LocalDateTime;
//DTO
public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        StatusTopico status,
        Long idAutor,
        String nombreAutor,
        Long idCurso,
        String nombreCurso,
        LocalDateTime fechaDeCreacion
) {
    public DatosRespuestaTopico(Topico nuevoTopico) {
        this(nuevoTopico.getId(),
                nuevoTopico.getTitulo(),
                nuevoTopico.getMensaje(),
                nuevoTopico.getStatus(),
                nuevoTopico.getAutor().getId(),
                nuevoTopico.getAutor().getNombre(),
                nuevoTopico.getCurso().getId(),
                nuevoTopico.getCurso().getNombre(),
                nuevoTopico.getFechaDeCreacion());
    }



}
