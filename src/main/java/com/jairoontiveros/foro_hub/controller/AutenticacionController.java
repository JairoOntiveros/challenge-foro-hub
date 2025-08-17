package com.jairoontiveros.foro_hub.controller;

import com.jairoontiveros.foro_hub.domain.usuario.DatosAutenticacion;
import com.jairoontiveros.foro_hub.domain.usuario.Usuario;
import com.jairoontiveros.foro_hub.infra.security.DatosTokenJWT;
import com.jairoontiveros.foro_hub.infra.security.ServicioToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private ServicioToken servicioToken;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(),datos.contrasena());
        var autenticacion = manager.authenticate(authenticationToken);

        var tokenJWT = servicioToken.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
