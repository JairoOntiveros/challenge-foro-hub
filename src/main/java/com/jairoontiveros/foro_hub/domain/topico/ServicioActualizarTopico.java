package com.jairoontiveros.foro_hub.domain.topico;

import com.jairoontiveros.foro_hub.domain.curso.CursoRepository;
import com.jairoontiveros.foro_hub.domain.usuario.UsuarioRepository;
import com.jairoontiveros.foro_hub.infra.exceptiones.ErrorDeValidacion;
import com.jairoontiveros.foro_hub.infra.exceptiones.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioActualizarTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public DatosRespuestaTopico actualizar(Long id,DatosActualizarTopico datos){
        Topico topico = topicoRepository.getReferenceById(id);

        //validaciones si el campo se envió pero está vacio.
        if (datos.titulo() != null && datos.titulo().isBlank()) {
            throw new ErrorDeValidacion("El título no puede estar vacío");
        }
        if (datos.texto() != null && datos.texto().isBlank()) {
            throw new ErrorDeValidacion("El texto no puede estar vacío");
        }

        //validacion de repetidos
        String nuevoTitulo = datos.titulo() != null ? datos.titulo():topico.getTitulo();
        String nuevoTexto = datos.texto() != null ? datos.texto():topico.getMensaje();
        if(topicoRepository.existsByTituloAndMensajeIgnoreCaseAndIdNot(nuevoTitulo,nuevoTexto, topico.getId())){
            throw new ErrorDeValidacion("Ya existe un tópico con el mismo titulo y texto");
        }

        //actualizar campos
        topico.actualizarTituloOMensaje(datos);
        topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

}
