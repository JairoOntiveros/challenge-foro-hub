create table usuarios(
id bigint not null auto_increment,
nombre varchar(255) not null,
correo_electronico varchar(255) not null,
contrasena varchar(255) not null,

primary key (id)
);


create table cursos(
id bigint not null auto_increment,
nombre varchar(255) not null,
categoria varchar(255),

primary key (id)
);


create table topicos(
id bigint not null auto_increment,
titulo varchar(255) not null,
mensaje text not null,
fecha_de_creacion timestamp default current_timestamp,
status varchar(20) not null,
autor_id bigint not null,
curso_id bigint not null,

primary key (id),
foreign key (autor_id) references usuarios(id),
foreign key (curso_id) references cursos(id)
);


create table respuestas (
id bigint not null auto_increment,
mensaje text not null,
topico_id bigint not null,
fecha_de_creacion timestamp default current_timestamp,
autor_id bigint not null,
solucion tinyint(1) default 0,

primary key (id),
foreign key (topico_id) references topicos(id),
foreign key (autor_id) references usuarios(id)

);