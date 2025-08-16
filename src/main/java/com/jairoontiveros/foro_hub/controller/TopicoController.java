package com.jairoontiveros.foro_hub.controller;

import com.jairoontiveros.foro_hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tópicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private ServicioRegistroDeTopicos servicioregistro;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos){

    DatosRespuestaTopico topicoCreado = servicioregistro.registrarTopico(datos);

    return ResponseEntity.status(HttpStatus.CREATED).body(topicoCreado);
    }

}
