# 🏥 Smart Clinic - Sistema de Gerenciamento Médico

Este projeto é um sistema completo de gerenciamento para uma clínica médica chamado **Smart Clinic**, desenvolvido como parte do meu portfólio no curso de Desenvolvedor Java pela IBM/Coursera.

> O objetivo principal é proporcionar um sistema funcional com login, autenticação JWT, dashboard protegido e cadastro de pacientes via tela HTML.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **MySQL** (ou outro banco relacional)
- **HTML5 + CSS3 + JavaScript Vanilla**
- **JWT (JSON Web Token)**
- **JPA/Hibernate**
- **Maven**

---

## ✅ Funcionalidades

### 🔐 Autenticação

- Registro e login de usuários com senha criptografada usando BCrypt
- Geração e validação de token JWT
- Proteção de rotas com autenticação baseada em token

### 👤 Dashboard Protegido

- Tela inicial com visualização dos pacientes cadastrados
- Apenas usuários autenticados conseguem acessar

### ➕ Cadastro de Pacientes

- Formulário HTML com nome, e-mail, telefone e CPF
- Validação básica e persistência no banco
- Envio autenticado com token JWT

### 📄 Navegação entre telas

- Login (`index.html`)
- Cadastro (`register.html`)
- Dashboard (`dashboard.html`)
- Cadastro de pacientes (`patient.html`)

---

## 📦 Como Executar Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/GilliardCarvalho/smart-clinic.git
   cd smart-clinic
   ```

2. Crie o banco de dados no MySQL (ex: `smart_clinic`)  
   > O script será executado automaticamente se estiver usando `spring.jpa.hibernate.ddl-auto=update`

3. Configure o `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/smart_clinic
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   jwt.secret=segredo_super_secreto
   ```

4. Execute o projeto:
   - Via terminal:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Ou rode diretamente via IDE (IntelliJ, VS Code, Eclipse...)

5. Acesse o sistema:
   - Frontend:
     ```
     http://localhost:8080/index.html
     ```
   - API (exemplo):
     ```
     POST http://localhost:8080/api/auth/login
     ```

---

## 🖼️ Prints

> Você pode adicionar aqui imagens como:
- Tela de login
- Dashboard com pacientes
- Cadastro de novo paciente

---

## 👨‍💻 Autor

Desenvolvido por **Gilliard Carvalho de Almeida**  
Aluno do curso **Desenvolvedor Java - IBM**  
📍 Jaraguá do Sul, SC | 🎓 Análise e Desenvolvimento de Sistemas  
🔗 [LinkedIn](https://www.linkedin.com/in/gilliard-almeida-52724b137/) | [GitHub](https://github.com/GilliardCarvalho/)

---

## 📌 Status do Projeto

✅ MVP concluído  
🚧 Futuras melhorias:
- Cadastro de médicos
- Agendamento de consultas
- Validações mais robustas
- Responsividade mobile

---

## 📝 Licença

Este projeto está licenciado sob a **MIT License**.  
Sinta-se livre para usar, modificar e contribuir!
