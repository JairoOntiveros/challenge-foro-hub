package com.jairoontiveros.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//DTO Request
public record DatosRegistroTopico(@NotBlank String titulo,
                                  @NotBlank String mensaje,
                                  @NotNull Long idAutor,
                                  @NotNull Long idCurso) {
}
