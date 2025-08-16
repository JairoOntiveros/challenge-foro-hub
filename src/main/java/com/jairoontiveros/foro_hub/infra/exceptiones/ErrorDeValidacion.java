package com.jairoontiveros.foro_hub.infra.exceptiones;

public class ErrorDeValidacion extends RuntimeException {

    public ErrorDeValidacion(String mensaje) {
        super(mensaje);
    }
}
