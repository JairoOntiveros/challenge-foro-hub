package com.jairoontiveros.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(@NotBlank String titulo,
                                  @NotBlank String texto,
                                  @NotNull Long idAutor,
                                  @NotNull Long idCurso) {
}
