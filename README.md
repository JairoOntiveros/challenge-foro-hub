<h1 align="center"> ForoHub API</h1>
<p align="center">
  API REST para gestión de tópicos en un foro, con Spring Boot, y Spring Security con JWT.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/STATUS-En%20Desarrollo-yellow" alt="Estado" />
  <img src="https://img.shields.io/badge/Java-17-blue" alt="Java 17" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.0-lightgrey" alt="Spring Boot 3" />
<img src="https://img.shields.io/badge/MariaDB-003545?logo=mariadb&logoColor=white" alt="MariaDB" />
</p>

---

##  Índice

- [Descripción](#descripción)
- [Estado del proyecto](#estado-del-proyecto)
- [Características](#características)
- [Demo](#demo)
- [Instalación](#instalación)
- [Tecnologías usadas](#tecnologías-usadas)
- [Endpoints principales](#endpoints-principales)
- [Contribuir](#contribuir)
- [Autor](#autor)
- [Licencia](#licencia)

---

##  Descripción

ForoHub API es una aplicación backend para gestionar tópicos en un foro. Permite crear, listar, detallar, actualizar y eliminar temas, todo protegido por autenticación JWT.

---

##  Estado del proyecto

 Proyecto en desarrollo — funcionalidad completa de tópicos, autenticación con JWT ya implementada.

---

##  Características

- Autenticación segura mediante login con JWT
- Operaciones CRUD 
- Validaciones contra duplicados y campos vacíos
- Seguridad Spring + JWT con filtros y protecciones activas

---

##  Demo

![login_demostracion](https://github.com/user-attachments/assets/a7f4e7d6-8b96-4818-b517-b45cc9f28d81)
![listar_demostracion](https://github.com/user-attachments/assets/82c8f2c9-084c-416d-b547-53926adbd437)

---

##  Instalación

1. **Clona el repositorio**

   ```bash
   git clone https://github.com/tu-usuario/foro-hub.git
   cd foro-hub
   ```
2. **Configura la base de datos MySQL/MariaDB
Crea la base de datos en tu servidor local:**
  ```sql
  CREATE DATABASE foro_hub_api;
  ```
3. **Configura las variables de entorno: Crea un archivo .env en la raíz del proyecto (o exporta variables en tu sistema) con el siguiente contenido:**
```env
DB_HOST= ip_o_nombre_del_servidor
DB_USER=tu_usuario_mysql
DB_PASSWORD=tu_password_mysql
JWT_SECRET=una_clave_secreta_segura
```
4. **Verifica application.properties
El proyecto y esta configurado para usar las variables:
```properties
spring.application.name=foro-hub

spring.datasource.url=jdbc:mysql://localhost/foro_hub_api
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

server.error.include-stacktrace=never

api.security.token.secret=${JWT_SECRET:12345678}

```
5.**Compila y ejecuta el proyecto**

6.**Accede a la API**

EL servidor quedara disponible por defecto en:
http://localhost:8080

---

## Base de datos

- MySQL / MariaDB
- Estructura de tablas y datos iniciales gestionados con Flyway
- Todas las migraciones se encuentran en `src/main/resources/db/migration`

---

## Tecnologías usadas

- Java 17

- Spring Boot 3

- Spring Security + JWT

- Spring Data JPA

- MariaDB

- Maven

- Insomnia (pruebas)

---

## Endpoints principales

- [Documentación](docs/endpoints.md)

POST /login — Generar token

GET /topicos — Listar tópicos

POST /topicos — Crear tópico nuevo

PUT /topicos/{id} — Actualizar tópico

DELETE /topicos/{id} — Eliminar tópico (físicamente)

GET /topicos/{id} — Detallar tópico

---  
## Autor
Jairo Ontiveros -- [Github](https://github.com/JairoOntiveros)

---

## Licencia

Este proyecto está bajo la licencia MIT.
