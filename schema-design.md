# 📊 Schema Design - Smart Clinic

Este documento descreve o esquema do banco de dados MySQL utilizado no projeto **Smart Clinic**. O sistema foi desenvolvido para gerenciar uma clínica médica, com funcionalidades para médicos, pacientes, consultas e autenticação de usuários.

---

## 🧩 Tabelas do Banco de Dados

### 1. `users`

| Campo       | Tipo         | Restrições     |
|-------------|--------------|----------------|
| id          | BIGINT       | PK, AUTO_INCREMENT |
| username    | VARCHAR(100) | NOT NULL, UNIQUE |
| password    | VARCHAR(255) | NOT NULL        |

---

### 2. `doctors`

| Campo          | Tipo         | Restrições         |
|----------------|--------------|--------------------|
| id             | BIGINT       | PK, AUTO_INCREMENT |
| name           | VARCHAR(100) | NOT NULL           |
| email          | VARCHAR(100) | NOT NULL, UNIQUE   |
| specialty      | VARCHAR(100) | NOT NULL           |
| availableTimes | TEXT         | JSON               |

---

### 3. `patients`

| Campo      | Tipo         | Restrições         |
|------------|--------------|--------------------|
| id         | BIGINT       | PK, AUTO_INCREMENT |
| name       | VARCHAR(100) | NOT NULL           |
| email      | VARCHAR(100) | NOT NULL, UNIQUE   |
| phone      | VARCHAR(20)  |                    |
| cpf        | VARCHAR(14)  | UNIQUE             |

---

### 4. `appointments`

| Campo             | Tipo        | Restrições                          |
|------------------|-------------|-------------------------------------|
| id               | BIGINT      | PK, AUTO_INCREMENT                  |
| appointmentTime  | DATETIME    | NOT NULL                            |
| doctor_id        | BIGINT      | FK → `doctors(id)`, NOT NULL        |
| patient_id       | BIGINT      | FK → `patients(id)`, NOT NULL       |

---

### 5. `prescriptions` (opcional, se aplicável)

| Campo           | Tipo         | Restrições                |
|----------------|--------------|---------------------------|
| id             | BIGINT       | PK, AUTO_INCREMENT        |
| details        | TEXT         | NOT NULL                  |
| appointment_id | BIGINT       | FK → `appointments(id)`   |

---

## 🔗 Relacionamentos

- Um `Doctor` possui muitos `Appointments`
- Um `Patient` possui muitos `Appointments`
- Um `Appointment` pertence a um `Doctor` e a um `Patient`
- Usuários (`users`) são usados para autenticação no sistema

---

## 🔐 Observações de Segurança

- Senhas dos usuários são armazenadas com hash BCrypt.
- A autenticação é feita com JWT Tokens.
- O backend valida os tokens antes de acessar rotas protegidas.

---

## 🛠️ Tecnologias

- **Banco de Dados**: MySQL
- **ORM**: JPA (Hibernate)
- **Spring Boot**, **Spring Security**

---

