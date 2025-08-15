package com.jairoontiveros.foro_hub.domain.usuario;

import com.jairoontiveros.foro_hub.domain.respuesta.Respuesta;
import com.jairoontiveros.foro_hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String contrasena;

    //relaciones

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Topico> topicos = new ArrayList<>();

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();
}
