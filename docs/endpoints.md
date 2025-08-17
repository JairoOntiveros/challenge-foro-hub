# Endpoints de ForoHub API

## Tipo de dato esperado

| Campo      | Tipo   | Descripción                               | Obligatorio |
|------------|--------|-------------------------------------------|------------|
| titulo     | String | Título del tópico                         | Sí         |
| mensaje    | String | Contenido o descripción del tópico        | Sí         |
| idCurso    | Long   | ID del curso asociado                      | Sí         |
| idAutor    | Long   | ID del usuario creador del tópico         | Sí         |
| status  | Enum      | Estado del tópico. Valores posibles: ABIERTO, CERRADO, RESUELTO| No         |



# Índice de Endpoints - ForoHub API

1. [Autenticación (Login)](#1-autenticación-login)
2. [Listar Tópicos](#2-listar-tópicos)
3. [Registrar Tópico](#3-registrar-tópico)
4. [Actualizar Tópico](#4-actualizar-tópico)
5. [Eliminar Tópico](#5-eliminar-tópico)
6. [Detallar Tópico](#6-detallar-tópico)


## 1. Autenticación (Login)

- **Método:** `POST`  
- **URI:** `/login`  
- **Autorización:** No requiere token  

### Body (JSON) esperado

```json
{
  "correoElectronico": "<correo_del_usuario>",
  "contrasena": "<contraseña_del_usuario>"
}
```
Reemplaza <correo_del_usuario> y <contraseña_del_usuario> por los datos registrados en la base de datos.

Response exitoso (200 OK)

```json
{
  "token": "<JWT_Token_generado>"
}
```
Response de error (401)

Si el correo o contraseña no coinciden, se retornará un error 401 Unauthorized.

## 2. Listar Tópicos

- **Método:** `GET`  
- **URI:** `/topicos`  
- **Autorización:** Requiere token JWT  

### Headers
Authorization: Bearer <JWT_Token>
Reemplaza `<JWT_Token>` por el token obtenido en el endpoint de login.

### Response exitoso (200 OK)

```json
[
  {
    "id": 1,
    "titulo": "<titulo_del_topico>",
    "mensaje": "<mensaje_del_topico>",
    "status": "ABIERTO",
    "idAutor": <id_del_autor>,
    "nombreAutor": "<nombre_del_autor>",
    "idCurso": <id_del_curso>,
    "nombreCurso": "<nombre_del_curso>",
    "fechaDeCreacion": "2025-08-17T11:00:00"
  },
  {
    "id": 2,
    "titulo": "<titulo_del_topico>",
    "mensaje": "<mensaje_del_topico>",
    "status": "CERRADO",
    "idAutor": <id_del_autor>,
    "nombreAutor": "<nombre_del_autor>",
    "idCurso": <id_del_curso>,
    "nombreCurso": "<nombre_del_curso>",
    "fechaDeCreacion": "2025-08-17T11:30:00"
  }
]
```

## 3. Registrar Tópico

- **Método:** `POST`  
- **URI:** `/topicos`  
- **Autorización:** Requiere token JWT  

### Headers

Authorization: Bearer <JWT_Token>

Reemplaza `<JWT_Token>` por el token obtenido en el endpoint de login

### Body (JSON) esperado

```json
{
  "titulo": "<titulo_del_topico>",
  "mensaje": "<mensaje_del_topico>",
  "idCurso": <id_del_curso>,
  "idAutor": <id_del_autor>
}
```
### Response exitoso (201 Created)

```json
{
  "id": <id_del_topico_creado>,
  "titulo": "<titulo_del_topico>",
  "mensaje": "<mensaje_del_topico>",
  "status": "ABIERTO",
  "idAutor": <id_del_autor>,
  "nombreAutor": "<nombre_del_autor>",
  "idCurso": <id_del_curso>,
  "nombreCurso": "<nombre_del_curso>",
  "fechaDeCreacion": "2025-08-17T12:00:00"
}
```
La API retorna el tópico recién creado con todos sus campos

### Response de error (400 Bad Request)

Si faltan campos obligatorios (titulo, mensaje) o ya existe un tópico con el mismo título y mensaje, se retorna un error 400 con un mensaje descriptivo.

## 4. Actualizar Tópico

- **Método:** `PUT`  
- **URI:** `/topicos/{id}`  
- **Autorización:** Requiere token JWT  

### Headers

Authorization: Bearer <JWT_Token>

Reemplaza `<JWT_Token>` por el token obtenido en el endpoint de login

Puedes enviar sólo los campos que deseas actualizar solo se pueden actualizar "titulo","mensaje" y "status". Todos son opcionales:

### Body (JSON) esperado
```json
{
  "titulo": "<nuevo_titulo_del_topico>",
  "mensaje": "<nuevo_mensaje_del_topico>",
  "status": "<nuevo_status_del_topico>"
}
```

### Response exitoso (200 OK)

```json
{
  "id": <id_del_topico>,
  "titulo": "<titulo_actualizado>",
  "mensaje": "<mensaje_actualizado>",
  "status": "<status_actualizado>",
  "idAutor": <id_del_autor>,
  "nombreAutor": "<nombre_del_autor>",
  "idCurso": <id_del_curso>,
  "nombreCurso": "<nombre_del_curso>",
  "fechaDeCreacion": "2025-08-17T12:00:00"
}
```

### Response de error (400 Bad Request)

Si el título o mensaje enviado está vacío, o si ya existe otro tópico con el mismo título y mensaje, se retornará un error 400 con un mensaje descriptivo.

### Response de error (404 Not Found)

Si el id del tópico no existe, se retornará un error 404 con un mensaje indicando que no se encontró el tópico.

## 5. Eliminar Tópico

- **Método:** `DELETE`  
- **URI:** `/topicos/{id}`  
- **Autorización:** Requiere token JWT

 ### Headers

Authorization: Bearer <JWT_Token>

Reemplaza `<JWT_Token>` por el token obtenido en el endpoint de login

### Ejemplo de request

DELETE /topicos/7
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...


### Response exitoso (204 No Content)

- No retorna contenido en el body.
- Indica que el tópico fue eliminado físicamente de la base de datos.

### Response de error (404 Not Found)

- Si el `id` del tópico no existe, se retornará un error `404` con un mensaje indicando que no se encontró el tópico.

## 6. Detallar Tópico

- **Método:** `GET`  
- **URI:** `/topicos/{id}`  
- **Autorización:** Requiere token JWT  

### Headers

Authorization: Bearer <JWT_Token>

Reemplaza `<JWT_Token>` por el token obtenido en el endpoint de login

### Ejemplo de request

GET /topicos/7
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.


### Response exitoso (200 OK)

```json
{
  "id": 7,
  "titulo": "Cómo manejar transacciones en Spring",
  "mensaje": "Estoy aprendiendo sobre @Transactional con ejemplos prácticos.",
  "status": "ABIERTO",
  "idAutor": 3,
  "nombreAutor": "Pam Beesly",
  "idCurso": 3,
  "nombreCurso": "Bases de Datos",
  "fechaDeCreacion": "2025-08-17T12:00:00"
}
```
Response de error (404 Not Found)

Si el id del tópico no existe, se retornará un error 404 indicando que no se encontró el tópico.

 


