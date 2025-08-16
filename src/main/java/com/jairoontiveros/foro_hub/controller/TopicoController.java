package com.jairoontiveros.foro_hub.controller;

import com.jairoontiveros.foro_hub.domain.topico.*;
import com.jairoontiveros.foro_hub.infra.exceptiones.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tópicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private ServicioRegistroDeTopicos servicioregistro;

    @Autowired
    ServicioActualizarTopico servicioActualizarTopico;

    @Autowired
    ServicioEliminarTopico eliminarTopico;


    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriBuilder){

    DatosRespuestaTopico topicoCreado = servicioregistro.registrarTopico(datos);
    URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoCreado.id()).toUri();
        return ResponseEntity.created(uri).body(topicoCreado);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listar(
            @PageableDefault(size = 10, sort = "fechaDeCreacion", direction = Sort.Direction.ASC)
    Pageable paginacion){
        Page<DatosRespuestaTopico> page = repository.findAll(paginacion).map(DatosRespuestaTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tópico con id " + id));

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizar(
            @PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos){

        var topicoActualizado = servicioActualizarTopico.actualizar(id,datos);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        eliminarTopico.eliminar(id);
        return  ResponseEntity.noContent().build();
    }

}
