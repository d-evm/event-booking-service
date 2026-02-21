# 🎟️ Online Event & Ticket Booking System

A production-grade backend system for an online event and ticket booking platform similar to BookMyShow.

Built using **Spring Boot, PostgreSQL, Redis, JWT Authentication, and Docker Compose**, following clean architecture and SOLID principles.

---

## 🚀 Tech Stack

- Java 21  
- Spring Boot 3.x  
- Spring Security + JWT  
- Spring Data JPA (Hibernate)  
- PostgreSQL  
- Redis (Caching)  
- Docker Compose  
- Maven  

---

## 🏗️ Architecture Overview

The system follows a clean layered architecture:

```
Controller → Service → Repository → Database
```
### Design Principles Applied

- Single Responsibility Principle (SRP)  
- Separation of concerns  
- Transactional integrity for booking flow  
- Role-based authorization  
- Infrastructure isolation (security, caching, persistence)  

---

## 🔐 Authentication & Authorization

- Passwords hashed using BCrypt  
- Stateless authentication using JWT  
- Role-based access control:
  - `ROLE_USER`
  - `ROLE_ADMIN`

### Custom JWT Claims

- `email`
- `roles`
- `userId`

Admin endpoints are protected via Spring Security configuration.

---

## 🎬 Core Features

### 1️⃣ User Management

- User registration  
- Secure login  
- JWT token generation  
- Profile retrieval  
- Role-based endpoint protection  

---

### 2️⃣ Event & Show Management

- Create events (admin only)  
- Create venues and auditoriums  
- Create shows for specific dates and times  

#### Event Metadata Support

- Genre  
- Language  
- Duration  
- Category  

---

### 3️⃣ Venue & Seat Layout

- Venue contains multiple auditoriums  
- Each auditorium has:
  - Rows  
  - Columns  
  - Seat categories  
  - Unique seat identifiers  
- Seat availability tracking per show  

---

## 🎟️ Booking Engine (Concurrency-Safe)

### 🔒 How Double Booking Is Prevented

The booking flow uses:

- `@Transactional`
- Database-level consistency  
- Seat availability verification inside transaction  
- Atomic status updates  

### 📌 Booking Flow

1. Validate seat availability  
2. Lock seats within transaction  
3. Persist booking  
4. Update seat status  
5. Commit transaction  

This ensures:

> Two users cannot book the same seat simultaneously.

---

## ⚡ Redis Caching

Used for optimizing read-heavy endpoints such as:

- Event listings  
- Active events browsing  

### Implementation

- Spring Cache abstraction  
- `@Cacheable`  
- Redis as cache store  

This reduces database load and improves response performance.

