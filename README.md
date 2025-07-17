# 📚 Biblioteca Digital de Livros

---

### Este projeto é uma aplicação Spring Boot com integração ao banco de dados Cloud Firestore (Firebase) que permite aos usuários cadastrarem, visualizarem, editarem e deletarem livros de sua biblioteca pessoal. Cada usuário possui uma coleção separada de livros, garantindo organização e privacidade.

---

## 🚀 Tecnologias Utilizadas

- **Java 21+**
- **Spring Boot**
- **Cloud Firestore (Banco de Dados NoSQL orientado a documentos - https://dbdb.io/db/cloud-firestore)**
- **Maven**

---

## 🧩 Funcionalidades

- **📌 Cadastro de usuários.**
- **📘 CRUD de livros por usuário (Create, Read, Update, Delete).**
- **🔐 Integração com Firebase (Firestore).**
- **🔒 Armazenamento seguro e escalável dos dados.**

---

## ✅ Realização de CRUDs

- **📘 Criar Livro: Salva um livro na coleção do usuário.**
- **📚 Listar Livros: Retorna todos os livros de um usuário.**
- **✏️ Editar Livro: Atualiza dados de um livro existente.**
- **❌ Deletar Livro: Remove um livro da coleção do usuário.**

---

## 💡 Como Rodar o Projeto

---

**1- Clone o repositório:**

  ```bash
    https://github.com/levibezerra/projeto-biblioteca-digital-de-livros.git
```

---

**2- Adicione seu arquivo json, onde você pode baixar no site https://console.firebase.google.com após realizar seu cadastro na plataforma. Por exemplo firebase-config.json adicione na pasta src/main/resources.**

---

**3- Execute o projeto pela IDE ou via terminal:**

  ```bash
    ./mvnw spring-boot:run
```

---