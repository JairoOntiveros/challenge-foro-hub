package com.jairoontiveros.foro_hub.infra.exceptiones;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
