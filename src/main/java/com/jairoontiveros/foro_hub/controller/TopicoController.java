package com.jairoontiveros.foro_hub.controller;

import com.jairoontiveros.foro_hub.domain.topico.DatosRegistroTopico;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tópicos")
public class TopicoController {

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datos){

    }

}
