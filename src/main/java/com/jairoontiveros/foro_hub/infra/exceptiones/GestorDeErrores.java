package com.jairoontiveros.foro_hub.infra.exceptiones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> manejarRecursoNoEncontrado(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //este maneja los errores de validacion automatica de Spring Validation (error400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorValidacion>> manejarError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        List<DatosErrorValidacion> lista = errores.stream()
                .map(DatosErrorValidacion::new)
                .toList();
        return ResponseEntity.badRequest().body(lista);
    }

    //mi excepcion personalizada (error 400)
    @ExceptionHandler(ErrorDeValidacion.class)
    public ResponseEntity<String> manejarErrorValidacion(ErrorDeValidacion ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> manejarJsonInvalido(HttpMessageNotReadableException ex){
       Map<String,String> error = new HashMap<>();
        error.put("mensaje","Error al leer el JSON: revisa la sintaxis.");
        error.put("detalle",ex.getMessage());
        return ResponseEntity.badRequest().body(error);

    }


    public record DatosErrorValidacion(String campo, String mensaje) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
