# Actividad M5 y M6

Este proyecto es una aplicación de lista de tareas desarrollada con Spring Boot.

## Configuración y Ejecución

### Dependencias

- Maven 3.9+
- JDK 17
- MySQL

### Configuración

1. Clona el repositorio:

    ```bash
    git clone https://github.com/tomaslenzi/SofttekActividadM5.git
    ```

2. Abre el proyecto en tu IDE preferido.

3. Configura las propiedades de conexión a la base de datos en `src/main/resources/application.properties`.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/todolistspring?serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=123456789
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=create
    ```

### Ejecución

- Antes de ejecutar la aplicación, asegúrate de tener el servidor MySQL en ejecución. Puedes
  usar [MySQL Workbench](https://www.mysql.com/products/workbench/) u otra herramienta de gestión de bases de datos.
- Ejecuta la aplicación desde tu IDE o mediante el siguiente comando:

```bash
./mvnw spring-boot:run
```

- Accede a http://localhost:8080/tasks en tu navegador.

## API REST

Además de la interfaz web, este proyecto ofrece una API REST para gestionar las tareas. Puedes probar los endpoints
utilizando herramientas como [Postman](https://www.postman.com/).

- **Obtener todas las tareas:** `GET http://localhost:8080/api/tasks`
- **Obtener una tarea por ID:** `GET http://localhost:8080/api/tasks/{id}`
- **Agregar una nueva tarea:** `POST http://localhost:8080/api/tasks`
- **Actualizar una tarea existente:** `PUT http://localhost:8080/api/tasks/{id}`
- **Eliminar una tarea:** `DELETE http://localhost:8080/api/tasks/{id}`

### Tecnologías Utilizadas

- Spring Boot
- MySQL
- HTML (para la interfaz web)

# DOCKERIZAR LA APLICACION

### Requisitos Previos

Antes de comenzar, asegúrate de tener las siguientes herramientas instaladas:

* Docker
* Docker Compose

## Pasos para Dockerizar la Aplicación

1. ### Creación del Docker Compose

   Se creó un archivo docker-compose.yml para orquestar los servicios necesarios. Primero, 
   se definió el servicio java_db utilizando la imagen de MySQL con la versión especificada.

```yml
version: "3.8"

services:
  java_db:
    container_name: java_db
    image: mysql:8.0
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: <contraseña>
      MYSQL_DATABASE: todolistspring
    volumes:
      - mysql-data:/var/lib/mysql
```
Luego, se ejecutó el siguiente comando en la terminal para crear y levantar el servicio java_db:

```bash
docker-compose up -d java_db
```

2. ### Creación del Archivo Dockerfile

   Se creó un archivo Dockerfile para construir la imagen de la aplicación Java Spring Boot.

```dockerfile
FROM openjdk:17-jdk-alpine

COPY target/SofttekActividadM5-0.0.1-SNAPSHOT.jar java_app.jar

ENTRYPOINT [ "java","-jar","java_app.jar" ]
```

3. ### Creación del Servicio java_app
   Se definió el servicio java_app en el archivo docker-compose.yml con la configuración necesaria y las variables de entorno requeridas por la aplicación.

```yml
  java_app:
    container_name: java_app
    image: tomaslenzi/java_app:1.0.0
    build: .
    ports:
      - "8080:8080"
    environment:
      - DATABASE_URL=jdbc:mysql://java_db:3306/todolistspring?createDatabaseIfNotExist=true
      - DATABASE_USERNAME=root
      - DATABASE_PASSWORD=<contraseña>
    depends_on:
     - java_db
```

4. ### Creación del JAR Java
   Se construyó el archivo JAR de la aplicación utilizando Maven.

```bash
mvn clean package 
```

5. ### Construcción de Imágenes y Levantamiento de Contenedores
   Se ejecutaron los siguientes comandos para construir las imágenes y levantar los contenedores.

```bash
docker-compose build
docker-compose up
```

# Nueva Configuración para Correr la Aplicación Dockerizada

* ## Clonar el Repositorio:

```bash
git clone https://github.com/tomaslenzi/SofttekActividadM5.git
```

* ## Ejecutar los Siguientes Comandos:

```bash
mvn clean package 
docker-compose up -d java_db
docker-compose up -d java_app
```

Ahora, la aplicación esta disponible en http://localhost:8080/tasks cuando se ejecutan en un entorno Dockerizado.