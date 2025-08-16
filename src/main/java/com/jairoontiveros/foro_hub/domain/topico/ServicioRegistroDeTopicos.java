package com.jairoontiveros.foro_hub.domain.topico;

import com.jairoontiveros.foro_hub.infra.exceptiones.ErrorDeValidacion;
import com.jairoontiveros.foro_hub.infra.exceptiones.ResourceNotFoundException;
import com.jairoontiveros.foro_hub.domain.curso.Curso;
import com.jairoontiveros.foro_hub.domain.curso.CursoRepository;
import com.jairoontiveros.foro_hub.domain.usuario.Usuario;
import com.jairoontiveros.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioRegistroDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datos) {

        if(topicoRepository.existsByTituloAndMensajeIgnoreCase(datos.titulo(),datos.mensaje())){
            throw new ErrorDeValidacion("Ya existe un topico con el mismo titulo y texto");
        }

        if (!usuarioRepository.existsById(datos.idAutor())) {
            throw new ResourceNotFoundException("No existe un usuario con el id proporcionado");
        }
        if (!cursoRepository.existsById(datos.idCurso())) {
            throw new ResourceNotFoundException("No existe un curso con el id proporcionado");
        }

        Usuario autor = usuarioRepository.findById(datos.idAutor()).get();
        Curso curso = cursoRepository.findById(datos.idCurso()).get();
        Topico nuevoTopico = new Topico(datos,autor,curso);
        topicoRepository.save(nuevoTopico);

        return new DatosRespuestaTopico(nuevoTopico);
    }
}
