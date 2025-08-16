package com.jairoontiveros.foro_hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    boolean existsByTituloAndMensajeIgnoreCase(String titulo, String mensaje);

    boolean existsByTituloAndMensajeIgnoreCaseAndIdNot(String titulo, String mensaje, Long id);

}
