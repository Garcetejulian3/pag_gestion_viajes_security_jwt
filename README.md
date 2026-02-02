# 🚍 Sistema de Gestión de Transporte

Proyecto full-stack orientado principalmente al backend, desarrollado con **Spring Boot** y autenticación mediante **JWT**, con un frontend separado construido en **HTML + CSS + JavaScript Vanilla** que consume la API REST.

El objetivo del proyecto es practicar arquitectura backend real, seguridad con tokens, consumo de APIs desde frontend y operaciones CRUD completas.

---

## 🧠 ¿Qué hace el proyecto?

La aplicación permite gestionar:

- Usuarios con autenticación
- Vehículos
- Choferes
- Viajes

Incluye:

- Login con JWT
- Protección de rutas con Spring Security
- CRUD completo de las entidades principales
- Consumo de la API desde frontend usando Fetch
- Manejo de tokens desde el navegador (localStorage)
- Validación automática del token mediante filtros

---

## ⚙️ Tecnologías usadas

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Maven

### Frontend
- HTML
- CSS (Tailwind CDN para prototipo)
- JavaScript Vanilla
- Fetch API

---

## 🏗️ Cómo está organizado

El backend sigue una arquitectura clásica por capas:

- Controller → recibe las peticiones
- Service → lógica de negocio
- Repository → acceso a datos
- DTOs → transferencia de datos
- Security → JWT + filtros + configuración

Spring Security se encarga de:

- Validar el token automáticamente
- Crear el contexto de autenticación
- Proteger las rutas `/api/**`

El frontend es totalmente independiente y solo consume la API.

---

## 🔐 Autenticación

La autenticación funciona con JWT:

1. El usuario inicia sesión desde el frontend.
2. El backend devuelve un token.
3. El token se guarda en `localStorage`.
4. Cada petición protegida envía el token en el header:


Spring Security intercepta cada request, valida el JWT y autoriza automáticamente.

Los controladores no manejan el token directamente.

---

## ▶️ Cómo ejecutar el proyecto

### 1️⃣ Backend

- Abrir el proyecto en el IDE
- Configurar la base de datos en `application.properties`
- Ejecutar:

ProyectoApplication.java


El backend corre en:



http://localhost:8080


---

### 2️⃣ Frontend

El frontend se abre por separado.

Los archivos HTML están en carpeta `static`.

Podés:

- Abrir los HTML directamente
- O usar Live Server desde VS Code

Ejemplo:



http://127.0.0.1:5500/home.html


Desde ahí el frontend consume la API del backend.

---

## 📌 Importante

Frontend y backend deben estar abiertos al mismo tiempo.

- Backend → puerto 8080
- Frontend → Live Server o navegador

El frontend solo funciona si el backend está activo.

---

## 🎯 Objetivo del proyecto

Proyecto realizado con fines educativos para:

- Practicar Spring Boot
- Implementar JWT real
- Separar frontend y backend
- Construir una API REST segura
- Manejar autenticación moderna
- Simular un entorno de trabajo backend real

---

## 👨‍💻 Autor

Julian Garcete  
Backend Developer Jr
