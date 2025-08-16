package com.jairoontiveros.foro_hub.domain.topico;

import com.jairoontiveros.foro_hub.infra.exceptiones.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioEliminarTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public void eliminar(Long id){
        if (!topicoRepository.existsById(id)){
            throw new ResourceNotFoundException("No se encontró el topico con id " +id);
        }
        topicoRepository.deleteById(id);
    }
}
