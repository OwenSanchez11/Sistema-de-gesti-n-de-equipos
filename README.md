<h1>Sistema de gestión de equipos</h1>
<p>Backend de un sistema de gestión de activos tecnológicos desarrollado con Java y Spring Boot. El objetivo del proyecto es centralizar 
  el control de equipos, usuarios y roles dentro de una organización, permitiendo en futuras versiones la gestión de préstamos, 
  mantenimientos y trazabilidad de los activos. El proyecto implementa una arquitectura RESTful, persistencia con una base de datos PostgreSQL y buenas prácticas de desarrollo backend.
</p>
<h3>Tecnologías usadas</h3>
<ul>
  <li>JAVA</li>
  <li>Spring boot</li>
  <li>Spring Data JPA</li>
  <li>PostgreSQL</li>
  <li>Maven</li>
</ul>

<h2>Estado Actual del proyecto</h2>

<h3>Funcionalidades</h3>
<h4>Gestión de usuarios</h4>
<ul>
  <li>Consultar usuarios</li>
  <li>Crear Usuarios</li>
  <li>Actualizar Usuario</li>
  <li>Eliminar Usuarios</li>
</ul>

<h4>Gestión de Roles</h4>
<ul>
  <li>Consultar roles</li>
  <li>Crear roles</li>
  <li>Actualizar roles</li>
  <li>Eliminar roles</li>
</ul>

<h4>Gestión de Equipos</h4>
<ul>
  <li>Consultar equipos</li>
  <li>Registrar equipos</li>
  <li>Actualizar equipos</li>
  <li>Eliminar equipos</li>
</ul>

<h4>Gestión de Prestamos</h4>
<ul>
  <li>Registrar préstamos de equipos</li>
  <li>Consultar historial de préstamos</li>
  <li>Consultar préstamo por ID</li>
  <li>Estados del préstamo:
  <ul>
    <li>ACTIVE</li>
    <li>RETURNED</li>
    <li>CANCELLED</li>
  </ul>
  </li>
</ul>

<h4>Gestión de Mantenimiento</h4>
<ul>
  <li>Consultar Mantenimientos</li>
  <li>Registrar Mantenimientos</li>
  <li>Actualizar estado del Mantenimientos</li>
  <li>Estados del Mantenimiento:
  <ul>
    <li>IN_PROGRESS</li>
    <li>COMPLETED</li>
    <li>CANCELLED</li>
  </ul>
  </li>
</ul>

<h4>Modelo de la DB</h4>
<span>Entidades principales</span>
<ul>
  <li>Role</li>
  <li>User</li>
  <li>Equipment</li>
  <li>Loan</li>
  <li>Maintenance</li>
</ul>

<h5>Relaciones</h5>
<ul>
  <li>Role 1 → N User</li>
  <li>Equipment 1 → N Loan</li>
  <li>User 1 → N Loan (Receiver)</li>
  <li>User 1 → N Loan (Deliverer)</li>
  <li>Equipment 1 → N Maintenance</li>
  <li>User 1 → N Maintenance (Register)</li>
</ul>

<h4>Manejo de excepciones globales</h4>
<ul>
  <li>Excepciones personalizadas para la lógica de negocio y recursos faltantes</li>
  <li>Respuestas de error HTTP estandarizadas(404,500,409)</li>
  <li>Gestión centralizada de excepciones con `@RestControllerAdvice`</li>
</ul>


<h4>Seguridad y autenticación con JWT y Spring Security</h4>
La API implementa un sistema de autenticación basado en **Spring Security** y **JSON Web Tokens (JWT)**, permitiendo proteger los recursos de la aplicación y controlar el acceso según el rol de cada usuario.
<h5>¿Como funciona el proceso?</h5>
Empezamos realizando la petición al siguiente endpoint, en donde el cliente le envía sus credenciales (`email` y `password`):

```text
Post /auth/login
```

aquí se envían el email y la contraseña del usuario, el controlador recibe esos datos y se los envía al servicio de autenticación
el cual lo que hará es comparar los datos del usuario ingresado.
<br>
Dentro del service se utiliza la clase `AuthenticationManager` el cual es el encargado de verificar los datos ingresados, esa responsabilidad se la delego a Spring Security

<ul>

<li>Spring Security valida las credenciales, generando un JWT si las credenciales son correctas, este token contiene información del user como email, rol y el id en el sistema</li>
<li>El token es devuelto al cliente para que pueda utilizarlo en las siguientes peticiones que desee realizar</li>
</ul>


```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer"
}
```




<h4>Documentación con swagger</h4>
<ul>
    <li>Se agregó la documentación con swagger de los modelos y las excepciones que lanza cada método</li>
</ul>




## 🔐 Autorización basada en roles

La API implementa un sistema de autorización utilizando **Spring Security**, **JWT (JSON Web Token)** y **roles almacenados en la base de datos**.

Después de que un usuario inicia sesión correctamente, se genera un JWT que contiene la información necesaria para identificar al usuario durante las siguientes peticiones.

En cada solicitud protegida:

1. El cliente envía el token en el encabezado `Authorization`.
2. El `JwtAuthenticationFilter` valida el token.
3. Si el token es válido, el usuario es autenticado y su información se almacena en el `SecurityContextHolder`.
4. Spring Security verifica los permisos del usuario mediante las anotaciones `@PreAuthorize`.

Los permisos se controlan utilizando los roles asignados a cada usuario. Actualmente el sistema implementa los siguientes:

- **ADMIN**
- **TECNICO**

Cada endpoint puede restringir el acceso a uno o varios roles utilizando anotaciones como:

```java
@PreAuthorize("hasRole('ADMIN')")
```

o

```java
@PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
```

Este enfoque permite separar la autenticación de la autorización, facilitando la administración de permisos y el mantenimiento del sistema.

---

## Configuración del proyecto

Por seguridad, el archivo `application.properties` no está incluido en el repositorio.

Para ejecutar el proyecto:

1. Crear un archivo:

```text
application.properties
```

2. Usar como referencia:

```text
application-example.properties
```

3. Configurar tus propias credenciales de base de datos y variables JWT.



## 🚀 Inicialización automática de la aplicación

La aplicación incorpora un mecanismo de inicialización automática mediante la interfaz `CommandLineRunner`.

Al iniciar el proyecto por primera vez con una base de datos vacía, la clase `DatabaseInitializer` verifica que existan los datos mínimos necesarios para que el sistema pueda utilizarse.

Actualmente realiza las siguientes tareas:

- Crea automáticamente los roles necesarios para el funcionamiento del sistema si no existen.
- Crea automáticamente el usuario administrador inicial si no existe.

Esto resuelve el problema del **primer usuario**, ya que la aplicación no cuenta con un endpoint de registro público. Todos los usuarios son creados posteriormente por un administrador autorizado.

Las credenciales del administrador inicial no se encuentran escritas directamente en el código fuente. En su lugar, se leen desde el archivo `application.properties`, lo que facilita su configuración sin necesidad de modificar la implementación.

```properties
app.admin.email=your_admin_email
app.admin.password=your_admin_password
```

Durante la inicialización:

1. Se verifica si existe un usuario con el correo configurado.
2. Si no existe, se recupera el rol **ADMIN** desde la base de datos.
3. Se crea el usuario administrador.
4. La contraseña es cifrada mediante **BCrypt** utilizando `PasswordEncoder`.
5. Finalmente, el usuario es almacenado en la base de datos.

Gracias a este mecanismo, cualquier persona que ejecute el proyecto con una base de datos vacía podrá iniciar sesión inmediatamente utilizando las credenciales configuradas en `application.properties`, sin necesidad de realizar configuraciones adicionales o crear manualmente el primer usuario.




## 📌 Main Endpoints


### 🔏 Autenticación
| Method | Endpoint      | Description |
| ------ |---------------|-------------|
| POST   | `/auth/login` | User Login  |



### 🔐 Role

| Method | Endpoint         | Description             |
| ------ | ---------------- | ----------------------- |
| POST   | `/roles`         | Create a new role       |
| GET    | `/roles`         | Retrieve all roles      |
| GET    | `/roles/{idRole}` | Retrieve a role by ID   |
| PUT    | `/roles/{idRol}` | Update role information |
| DELETE | `/roles/{idRol}` | Delete a role           |

---

### 👤 User

| Method | Endpoint                     | Description             |
| ------ |------------------------------| ----------------------- |
| POST   | `/users`                     | Create a new user       |
| GET    | `/users`                     | Retrieve all users      |
| GET    | `/users/{idUser}`            | Retrieve a user by ID   |
| PUT    | `/users/{idUser}` | Update user information |
| DELETE | `/users/{idUser}` | Delete a user           |

---

### 🛠 Equipment

| Method | Endpoint                           | Description                  |
| ------ | ---------------------------------- | ---------------------------- |
| POST   | `/equipments`        | Register a new equipment     |
| GET    | `/equipments`                  | Retrieve all equipment       |
| GET    | `/equipments/{idEquipment}`        | Retrieve equipment by ID     |
| PUT    | `/equipments/{idEquipment}` | Update equipment information |
| DELETE | `/equipments/{idEquipment}` | Delete equipment             |

---

### 📦 Loan

| Method | Endpoint                  | Description               |
| ------ | ------------------------- | ------------------------- |
| POST   | `/loan`            | Create a new loan         |
| GET    | `/loan`                | Retrieve all loans        |
| GET    | `/loan/{idLoan}`          | Retrieve a loan by ID     |
| PUT    | `/loan/{idLoan}` | Register equipment return |
| DELETE | `/loan/{idLoan}`   | Delete a loan record      |

---

### 🔧 Maintenance

| Method | Endpoint                                         | Description                         |
| ------ | ------------------------------------------------ | ----------------------------------- |
| POST   | `/maintenance`                   | Create a maintenance record         |
| GET    | `/maintenance`                               | Retrieve all maintenance records    |
| GET    | `/maintenance/{idMaintenance}`           | Retrieve a maintenance record by ID |
| PUT    | `/maintenance/{idMaintenance}` | Update maintenance status           |


## ⚠️ API Error Handling

| HTTP Status | Exception | Description |
|------------|-----------|-------------|
| 404 | `ResourceNotFoundException` | Requested resource does not exist |
| 409 | `BusinessRuleException` | Operation violates business logic rules |
| 500 | `Exception` | Unexpected internal server error |
| 401 | `InvalidCredentialesException` | Unauthorized | 
| 400 | `Validation exception` | Validation error |


## Funcionalidades Avanzadas de consultas

La API incorpora funcionalidades para las consultas de información utilizando **Spring Data JPA** permitiendo obtener resultados flexibles, eficientes y escalables.
### 📄 Paginación
Permite consultar grandes volúmenes de información dividiendo los resultados en páginas.

**Ejemplo:**

```http
GET /users?page=0&size=10
```

### 🔀 Ordenamiento dinámico

Los resultados pueden ordenarse por cualquier atributo válido de la entidad, tanto de forma ascendente como descendente.

**Ejemplo:**

```http
GET /users?sortBy=name&direction=ASC
```

---
### 🔍 Filtros dinámicos con Spring Data JPA Specifications

La API permite aplicar filtros opcionales mediante **Spring Data JPA Specifications**, evitando la necesidad de crear múltiples métodos en los repositorios para cada combinación posible de filtros.

Actualmente se pueden realizar consultas como:

```http
GET /users?active=true
```

```http
GET /users?email=ejemplo@admin.com
```
```http
GET /equipment?name=Dell
```

```http
GET /equipment?name=Dell&seriesNum=1001
```

```http
GET /maintenance?idEquipment=3
```

Los filtros pueden combinarse libremente según las necesidades de la consulta.

---

### ⚡ Combinación de funcionalidades

La paginación, el ordenamiento y los filtros dinámicos pueden utilizarse en una misma petición.

**Ejemplo:**

```http
GET /users?page=0&size=10&sortBy=name&direction=ASC&active=true
```

Esta implementación proporciona una arquitectura escalable y fácil de mantener, permitiendo incorporar nuevos filtros sin modificar la estructura del repositorio ni crear múltiples métodos de consulta.



## Progreso del proyecto

| Funcionalidad | Estado |
|--------------|--------|
| CRUD completo | ✅ Completado |
| DTOs | ✅ Completado |
| Manejo de excepciones | ✅ Completado |
| Encriptación de contraseñas | ✅ Completado |
| Login de usuarios | ✅ Completado |
| Generación de JWT | ✅ Completado |
| Validación de JWT | ✅ Completado |
| Configuración Spring Security | ✅ Completado |
| Autorización por roles | ✅ Completado |
| Docker | ⏳ Planeado |
| Deploy | ⏳ Planeado |


<p>Este proyecto continúa evolucionando con el objetivo de implementar una API backend segura, escalable y alineada con buenas prácticas de desarrollo profesional.</p>

<h3>Autor</h3>
Owen Sanchez - Backend Developer en formación enfocado en Java, Spring Boot y PostgreSQL.
