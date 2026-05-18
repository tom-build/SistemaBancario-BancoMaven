# 🏦 Sistema Bancário em Java (Maven)

Sistema bancário desenvolvido em **Java**, utilizando **Maven**, com foco no estudo de **orientação a objetos**, **persistência de dados**, **testes automatizados** e boas práticas de arquitetura backend.

O projeto simula operações básicas de um banco, permitindo a manipulação de contas e transações financeiras em um ambiente controlado com persistência em banco de dados SQLite.

---

## 📌 Objetivo do Projeto

Este projeto foi criado com fins **educacionais**, visando:

- praticar programação orientada a objetos com Java
- compreender a estrutura de projetos Maven
- trabalhar com persistência de dados utilizando JDBC
- aplicar validações e regras de negócio
- estudar arquitetura em camadas
- implementar testes automatizados
- compreender desacoplamento e injeção de dependência

---

## 🛠 Tecnologias Utilizadas

- Java  
- Maven  
- JDBC  
- SQLite  
- JUnit 5  
- Git e GitHub  

---

## 🧠 Conceitos e Skills Praticadas

### Programação Orientada a Objetos
- Classes abstratas
- Herança
- Encapsulamento
- Polimorfismo
- Sobrescrita de métodos

### Backend e Arquitetura
- Arquitetura em camadas (`model`, `dao`, `service`, `config`, `util`)
- Separação de responsabilidades
- Injeção de dependência
- Desacoplamento entre camadas
- Reutilização de componentes
- Persistência de dados com JDBC

### Banco de Dados
- Integração com SQLite
- Operações CRUD
- Prepared Statements
- Manipulação de ResultSet
- Geração automática de IDs
- Banco de testes isolado (`bancoTest.db`)

### Testes Automatizados
- Testes unitários
- Testes de integração com persistência real
- Uso do JUnit 5
- Criação e limpeza automática de tabelas durante os testes
- Validação de regras de negócio
- Testes das camadas Model, DAO e Service

### Versionamento
- Commits semânticos
- Organização de histórico no Git
- Estruturação profissional de repositório

---

## ✨ Funcionalidades

- Criar contas bancárias  
- Realizar depósitos  
- Realizar saques  
- Realizar transferências  
- Consultar saldo  
- Buscar conta por ID  
- Excluir contas  
- Persistência de dados em banco local SQLite  
- Validação de entradas e regras de negócio  
- Testes automatizados com banco separado do ambiente principal  

---

## 🧪 Testes Implementados

O projeto possui testes automatizados utilizando **JUnit 5** com persistência real em SQLite.

Foram implementados testes para:

- validação de criação de contas
- depósitos e saques
- busca por ID
- listagem de contas
- persistência de dados na camada DAO
- conexão com banco de testes
- validações de regras de negócio

Os testes utilizam um banco separado (`bancoTest.db`) para evitar interferência nos dados da aplicação principal.

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven instalado
- IDE de sua preferência (IntelliJ, Eclipse, VS Code)

---

## 🚀 Passos

1. Clone o repositório:

```bash
git clone https://github.com/tomzudo/SistemaBancario-BancoMaven.git
```

2. Entre na pasta do projeto:

```bash
cd SistemaBancario-BancoMaven
```

3. Compile o projeto:

```bash
mvn compile
```

4. Execute a aplicação:

```bash
mvn exec:java
```

---

## 📂 Estrutura do Projeto

```txt
src
├── main
│   └── java/com/banco
│       ├── config
│       ├── dao
│       ├── model
│       ├── service
│       └── util
│
└── test
    └── java/com/banco
        ├── config
        ├── dao
        ├── model
        └── service
```

---

## 📖 Aprendizados

Durante o desenvolvimento deste projeto foram praticados conceitos importantes do ecossistema backend Java, incluindo:

- fluxo completo de persistência com JDBC
- manipulação de conexões SQL
- desacoplamento entre camadas
- injeção de dependência manual
- testes de integração com banco real
- organização profissional de projeto Maven
- estruturação de código voltada para manutenção e escalabilidade