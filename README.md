# 3º Checkpoint - Java Advanced

### Objetivo do Projeto:
Desenvolver uma aplicação em Java com Spring Boot e Gradle que gerencie um sistema de
clínicas médicas. A aplicação permitirá que administradores registrem e gerenciem
clínicas, médicos e pacientes, enquanto pacientes podem marcar consultas e acessar
suas informações médicas.


## Integrantes do Grupo

- Kaique Santos de Andrade - RM99562
- Marcelo Augusto de Mello Paixão - RM99466
- Rodrigo Batista Freire - RM99513

## Login ao Executar

#### Acesso Administrador
- Usuário: admin
- Senha: admin

#### Acesso Usuário
- Usuário: user
- Senha: user


## Acessos ao [H2 - Database](http://localhost:8080/h2-console)

public/default:

![image](https://github.com/FIAP-2024/CP3_MedicalClinicManagement/assets/80494196/bd311d10-b46e-44f3-a633-3a917ea0e60e)

- JDBC URL: jdbc:h2:mem:publicdb
- Username: public
- Password: public

##

dev/desenvolvedor:

![image](https://github.com/FIAP-2024/CP3_MedicalClinicManagement/assets/80494196/58a7afa8-3c24-4786-8b38-2ebe60c4d7a1)

- JDBC URL: jdbc:h2:mem:devdb
- Username: dev
- Password: admin

prod/Produção:

![image](https://github.com/FIAP-2024/CP3_MedicalClinicManagement/assets/80494196/cb857538-ab3e-4982-bc17-99d4c216ec42)

- JDBC URL: jdbc:h2:mem:proddb
- Username: prod
- Password: prod

## Acesso ao [Swagger - Documentação da API](http://localhost:8080/swagger-ui/index.html)

![image](https://github.com/FIAP-2024/CP3_MedicalClinicManagement/assets/80494196/334ae099-ffcb-4965-99f7-a7e874e0d5de)

## Endpoints da API

### Perfil Default/Padrão:

#### Pacientes

- **URL Base:** `http://localhost:8080/api/public/patients`
- **Métodos:**
  - **POST**: Criar um novo paciente
  - **GET**: Obter todos os pacientes
  - **GET by ID**: `http://localhost:8080/api/public/patients/{id}` - Obter um paciente por ID
  - **PUT by ID**: `http://localhost:8080/api/public/patients/{id}` - Atualizar um paciente por ID
  - **DELETE by ID**: `http://localhost:8080/api/public/patients/{id}` - Deletar um paciente por ID

#### Médicos

- **URL Base:** `http://localhost:8080/api/public/doctors`
- **Métodos:**
  - **POST**: Criar um novo médico
  - **GET**: Obter todos os médicos
  - **GET by ID**: `http://localhost:8080/api/public/doctors/{id}` - Obter um médico por ID
  - **PUT by ID**: `http://localhost:8080/api/public/doctors/{id}` - Atualizar um médico por ID
  - **DELETE by ID**: `http://localhost:8080/api/public/doctors/{id}` - Deletar um médico por ID

#### Clínicas

- **URL Base:** `http://localhost:8080/api/public/clinics`
- **Métodos:**
  - **POST**: Criar uma nova clínica
  - **GET**: Obter todas as clínicas
  - **GET by ID**: `http://localhost:8080/api/public/clinics/{id}` - Obter uma clínica por ID
  - **PUT by ID**: `http://localhost:8080/api/public/clinics/{id}` - Atualizar uma clínica por ID
  - **DELETE by ID**: `http://localhost:8080/api/public/clinics/{id}` - Deletar uma clínica por ID

#### Consultas

- **URL Base:** `http://localhost:8080/api/public/appointments`
- **Métodos:**
  - **POST**: Criar uma nova consulta
  - **GET**: Obter todas as consultas
  - **GET by ID**: `http://localhost:8080/api/public/appointments/{id}` - Obter uma consulta por ID
  - **PUT by ID**: `http://localhost:8080/api/public/appointments/{id}` - Atualizar uma consulta por ID
  - **DELETE by ID**: `http://localhost:8080/api/public/appointments/{id}` - Deletar uma consulta por ID

##

### Perfil Dev/Desenvolvimento:

#### Pacientes

- **URL Base:** `http://localhost:8080/api/dev/patients`
- **Métodos:**
  - **POST**: Criar um novo paciente
  - **GET**: Obter todos os pacientes
  - **GET by ID**: `http://localhost:8080/api/dev/patients/{id}` - Obter um paciente por ID
  - **PUT by ID**: `http://localhost:8080/api/dev/patients/{id}` - Atualizar um paciente por ID
  - **DELETE by ID**: `http://localhost:8080/api/dev/patients/{id}` - Deletar um paciente por ID

#### Médicos

- **URL Base:** `http://localhost:8080/api/dev/doctors`
- **Métodos:**
  - **POST**: Criar um novo médico
  - **GET**: Obter todos os médicos
  - **GET by ID**: `http://localhost:8080/api/dev/doctors/{id}` - Obter um médico por ID
  - **PUT by ID**: `http://localhost:8080/api/dev/doctors/{id}` - Atualizar um médico por ID
  - **DELETE by ID**: `http://localhost:8080/api/dev/doctors/{id}` - Deletar um médico por ID

#### Clínicas

- **URL Base:** `http://localhost:8080/api/dev/clinics`
- **Métodos:**
  - **POST**: Criar uma nova clínica
  - **GET**: Obter todas as clínicas
  - **GET by ID**: `http://localhost:8080/api/dev/clinics/{id}` - Obter uma clínica por ID
  - **PUT by ID**: `http://localhost:8080/api/dev/clinics/{id}` - Atualizar uma clínica por ID
  - **DELETE by ID**: `http://localhost:8080/api/dev/clinics/{id}` - Deletar uma clínica por ID

#### Consultas

- **URL Base:** `http://localhost:8080/api/dev/appointments`
- **Métodos:**
  - **POST**: Criar uma nova consulta
  - **GET**: Obter todas as consultas
  - **GET by ID**: `http://localhost:8080/api/dev/appointments/{id}` - Obter uma consulta por ID
  - **PUT by ID**: `http://localhost:8080/api/dev/appointments/{id}` - Atualizar uma consulta por ID
  - **DELETE by ID**: `http://localhost:8080/api/dev/appointments/{id}` - Deletar uma consulta por ID

##

### Perfil Prod/Produção:

#### Pacientes

- **URL Base:** `http://localhost:8080/api/prod/patients`
- **Métodos:**
  - **POST**: Criar um novo paciente
  - **GET**: Obter todos os pacientes
  - **GET by ID**: `http://localhost:8080/api/prod/patients/{id}` - Obter um paciente por ID
  - **PUT by ID**: `http://localhost:8080/api/prod/patients/{id}` - Atualizar um paciente por ID
  - **DELETE by ID**: `http://localhost:8080/api/prod/patients/{id}` - Deletar um paciente por ID

#### Médicos

- **URL Base:** `http://localhost:8080/api/prod/doctors`
- **Métodos:**
  - **POST**: Criar um novo médico
  - **GET**: Obter todos os médicos
  - **GET by ID**: `http://localhost:8080/api/prod/doctors/{id}` - Obter um médico por ID
  - **PUT by ID**: `http://localhost:8080/api/prod/doctors/{id}` - Atualizar um médico por ID
  - **DELETE by ID**: `http://localhost:8080/api/prod/doctors/{id}` - Deletar um médico por ID

#### Clínicas

- **URL Base:** `http://localhost:8080/api/prod/clinics`
- **Métodos:**
  - **POST**: Criar uma nova clínica
  - **GET**: Obter todas as clínicas
  - **GET by ID**: `http://localhost:8080/api/prod/clinics/{id}` - Obter uma clínica por ID
  - **PUT by ID**: `http://localhost:8080/api/prod/clinics/{id}` - Atualizar uma clínica por ID
  - **DELETE by ID**: `http://localhost:8080/api/prod/clinics/{id}` - Deletar uma clínica por ID

#### Consultas

- **URL Base:** `http://localhost:8080/api/prod/appointments`
- **Métodos:**
  - **POST**: Criar uma nova consulta
  - **GET**: Obter todas as consultas
  - **GET by ID**: `http://localhost:8080/api/prod/appointments/{id}` - Obter uma consulta por ID
  - **PUT by ID**: `http://localhost:8080/api/prod/appointments/{id}` - Atualizar uma consulta por ID
  - **DELETE by ID**: `http://localhost:8080/api/prod/appointments/{id}` - Deletar uma consulta por ID
