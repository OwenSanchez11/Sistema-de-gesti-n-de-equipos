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

<h4>Documentación con swagger</h4>
<ul>
    <li>Se agregó la documentación con swagger de los modelos y las excepciones que lanza cada método</li>
</ul>

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




## 📌 Main Endpoints

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
| Autorización por roles | 🔄 En desarrollo |
| Docker | ⏳ Planeado |
| Deploy | ⏳ Planeado |


<p>Este proyecto continúa evolucionando con el objetivo de implementar una API backend segura, escalable y alineada con buenas prácticas de desarrollo profesional.</p>

<h3>Autor</h3>
Owen Sanchez - Backend Developer en formación enfocado en Java, Spring Boot y PostgreSQL.
