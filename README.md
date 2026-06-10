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

<h4>Endpoints principales</h4>

### 👤 User

| Method | Endpoint            | Description             |
| ------ | ------------------- | ----------------------- |
| POST   | `/user/save`        | Create a new user       |
| GET    | `/user/all`         | Get all users           |
| GET    | `/user/{id}`        | Get user by ID          |
| PUT    | `/user/update/{id}` | Update user information |

---

### 🛠 Equipment

| Method | Endpoint                 | Description                  |
| ------ | ------------------------ | ---------------------------- |
| POST   | `/equipment/save`        | Register a new equipment     |
| GET    | `/equipment/all`         | Get all equipment            |
| GET    | `/equipment/{id}`        | Get equipment by ID          |
| PUT    | `/equipment/update/{id}` | Update equipment information |

---

### 📦 Loan

| Method | Endpoint                    | Description               |
| ------ | --------------------------- | ------------------------- |
| POST   | `/loan/saveLoan`            | Create a new loan         |
| GET    | `/loan/all`                 | Get all loans             |
| GET    | `/loan/{id}`                | Get loan by ID            |
| PUT    | `/loan/updateLoan/{idLoan}` | Register equipment return |

---

### 🔧 Maintenance

| Method | Endpoint                       | Description                 |
| ------ | ------------------------------ | --------------------------- |
| POST   | `/maintenance/saveMaintenance` | Create a maintenance record |
| GET    | `/maintenance/all`             | Get all maintenance records |
| GET    | `/maintenance/{id}`            | Get maintenance by ID       |
| PUT    | `/maintenance/update/{id}`     | Update maintenance status   |






<h3>Estado actual del proyecto</h3>
<span>Proyecto en desarrollo</span>
<h4>Próximas mejoras</h4>
<ul>
  <li>Manejo global de excepciones</li>
  <li>Validaciones de negocio</li>
  <li>DTOs optimizados</li>
  <li>Historial de mantenimientos por equipo</li>
  <li>Seguridad con JWT</li>
</ul>

<h3>Autor</h3>
Owen Sanchez
Backend Developer en formación enfocado en Java, Spring Boot y PostgreSQL.
