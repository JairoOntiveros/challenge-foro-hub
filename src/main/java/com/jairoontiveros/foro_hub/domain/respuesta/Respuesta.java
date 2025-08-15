package com.jairoontiveros.foro_hub.domain.respuesta;


import com.jairoontiveros.foro_hub.domain.topico.Topico;
import com.jairoontiveros.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    //de nuevo la fecha no es actualizable
    @Column(name = "fecha_de_creacion",nullable = false,updatable = false)
    private LocalDateTime fechaDeCreacion = LocalDateTime.now();

    private boolean solucion;

    //relaciones

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
}
