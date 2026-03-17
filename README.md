# Copa America 2024

Aplicacion web desarrollada con Spring Boot para mostrar informacion sobre la Copa America 2024 disputada en Estados Unidos. El proyecto combina una portada estatica inspirada en el torneo con una seccion dinamica de selecciones, jugadores y directores tecnicos cargados desde base de datos.

## Que ofrece el proyecto

- Home con cuadro final, grupos y noticias destacadas del torneo.
- Listado de selecciones registradas.
- Pagina individual por seleccion con datos historicos, imagen principal, bandera, plantel y DT.
- Formularios para cargar equipos, jugadores y directores tecnicos.
- Almacenamiento de imagenes en MySQL como `LONGBLOB`.
- Vistas server-side renderizadas con Thymeleaf.

## Stack utilizado

- Java 17
- Spring Boot 3.3.2
- Spring Web
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- Maven Wrapper (`mvnw`, `mvnw.cmd`)

## Como esta organizado

```text
src/main/java/com/sperez/copaamerica
|- controllers    -> rutas MVC para home, equipos, jugadores, DT e imagenes
|- entities       -> modelo de datos (Team, Player, Dt, Photo)
|- repositories   -> acceso a datos con Spring Data JPA
|- services       -> logica de negocio y validaciones
|- exceptions     -> excepcion de dominio

src/main/resources
|- templates      -> vistas Thymeleaf
|- static/css     -> estilos
|- static/img     -> recursos visuales estaticos
|- application.properties
```

## Modelo principal

### `Team`
Representa una seleccion. Guarda nombre, codigo corto, apodo, debut, participaciones, historia, titulos, maximo goleador y tres imagenes asociadas.

### `Player`
Representa un jugador vinculado a una seleccion y con una foto propia.

### `Dt`
Representa al director tecnico de una seleccion, tambien con foto.

### `Photo`
Entidad usada para persistir imagenes en base de datos y servirlas luego desde la ruta `/imagen/{id}`.

## Rutas principales

- `/` -> portada principal.
- `/equipos` -> listado de selecciones.
- `/equipos/{teamName}` -> detalle de una seleccion.
- `/equipos/registrar` -> alta basica de equipos.
- `/equipos/modificar` -> carga de informacion extendida e imagenes de cada seleccion.
- `/jugadores/registrar` -> alta de jugadores.
- `/dt/registrar` -> alta de directores tecnicos.
- `/imagen/{id}` -> recuperacion de imagenes almacenadas en la base.

## Requisitos

- Java 17 instalado
- MySQL corriendo localmente
- Base de datos `copaamerica` creada
- Usuario y clave configurados en `application.properties`

Configuracion actual del proyecto:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/copaamerica?allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=GMT&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

Si tu entorno usa otras credenciales, cambia esos valores antes de iniciar la aplicacion.

## Como ejecutarlo

1. Crear la base de datos:

```sql
CREATE DATABASE copaamerica;
```

2. Iniciar el proyecto con Maven Wrapper:

```bash
./mvnw spring-boot:run
```

En Windows tambien podes usar:

```powershell
.\mvnw.cmd spring-boot:run
```

3. Abrir en el navegador:

```text
http://localhost:8080/
```

## Flujo recomendado de carga

Como varias vistas dependen de datos e imagenes cargadas en la base, conviene seguir este orden:

1. Registrar un equipo desde `/equipos/registrar`.
2. Completar su informacion e imagenes desde `/equipos/modificar`.
3. Cargar jugadores desde `/jugadores/registrar`.
4. Cargar el DT desde `/dt/registrar`.
5. Revisar el resultado en `/equipos` y luego en `/equipos/{teamName}`.

## Estado actual del proyecto

- La home contiene contenido mayormente estatico del torneo 2024.
- La seccion de equipos si depende de la informacion almacenada en MySQL.
- La seguridad esta configurada para permitir acceso a todas las rutas y tiene CSRF deshabilitado, algo util para desarrollo pero no ideal para produccion.
- El repo incluye la carpeta `target/`, que corresponde a archivos generados de compilacion y normalmente no hace falta versionar.
- Hay textos con problemas de codificacion en algunas plantillas, por lo que puede aparecer texto con acentos mal renderizados si los archivos no estan guardados en UTF-8 correctamente.

## Ideas para seguir mejorandolo

- Agregar capturas del sitio en este README.
- Incorporar datos iniciales para no depender de carga manual.
- Proteger formularios administrativos.
- Mejorar validaciones y manejo de errores.
- Separar mejor contenido estatico de contenido dinamico.
- Agregar tests de integracion y documentacion de despliegue.

## Autor

Proyecto realizado por Sebastian Perez.
