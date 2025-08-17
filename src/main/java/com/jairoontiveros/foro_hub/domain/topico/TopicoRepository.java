package com.jairoontiveros.foro_hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicoRepository extends JpaRepository<Topico,Long> {

    boolean existsByTituloAndMensajeIgnoreCase(String titulo, String mensaje);

    boolean existsByTituloAndMensajeIgnoreCaseAndIdNot(String titulo, String mensaje, Long id);

}
