alter table usuarios
add constraint unique_correo unique (correo_electronico);

alter table usuarios
add constraint unique_nombre unique(nombre);