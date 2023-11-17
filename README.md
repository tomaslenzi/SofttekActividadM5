# Actividad M5

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
    spring.jpa.hibernate.ddl-auto=update
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

