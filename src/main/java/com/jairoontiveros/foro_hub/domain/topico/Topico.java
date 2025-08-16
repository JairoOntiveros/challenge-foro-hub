package com.jairoontiveros.foro_hub.domain.topico;

import com.jairoontiveros.foro_hub.domain.curso.Curso;
import com.jairoontiveros.foro_hub.domain.respuesta.Respuesta;
import com.jairoontiveros.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name = "Topico")
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    //Indicando a JPA que este dato se guarda como TEXT en la DB, y no como char(255)
    @Column(columnDefinition = "TEXT")
    private String mensaje;

    //la fecha de creacion no puede ser cambiada.
    @Column(name = "fecha_de_creacion", nullable = false,updatable = false)
    private LocalDateTime fechaDeCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    //relaciones

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id",nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "topico",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();


    public Topico(@Valid DatosRegistroTopico datos, Usuario autor, Curso curso) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.status = StatusTopico.ABIERTO;
        this.autor =autor;
        this.curso =curso;
    }

    public void actualizarTituloOMensaje(DatosActualizarTopico datos) {
        if (datos.titulo()!= null) this.titulo = datos.titulo();
        if (datos.texto() != null) this.mensaje = datos.texto();
        if (datos.status() != null) this.status = datos.status();
    }
}
