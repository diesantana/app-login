# Login App
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/diesantana/app-login/blob/main/LICENSE) 



## Sobre o projeto
Login App é uma aplicação Fullstack de login e autenticação. 
O projeto é desenolvido utilizando Angular no front-end e Spring Boot no backend. 
O objetivo do projeto é reforçar o meu aprendizado com Spring Security e a integração do front-end com o backend.

### Layout mobile
<img src="https://github.com/diesantana/assets/blob/main/img/login-api/mobile-login.png?raw=true" alt="Mobile 1" width="200" /> 
<img src="https://github.com/diesantana/assets/blob/main/img/login-api/mobile-register.png?raw=true" alt="Mobile 2" width="200" />

### Layout Web
<img src="https://github.com/diesantana/assets/blob/main/img/login-api/full-login.png?raw=true" alt="Web 1" width="500" /> 
<img src="https://github.com/diesantana/assets/blob/main/img/login-api/full-register.png?raw=true" alt="Web 2" width="500" />
<br>
<br>

## Tecnologias utilizadas
### Back end
- **Linguagem:** Java 17
- **Framework:** Spring Boot 3.2.7
- **ORM:** JPA / Hibernate
- **Gerenciamento de dependências:** Maven


### Front end
- **Framework:** Angular 17.3.0
- **Biblioteca de componentes:** Bootstrap 5.3.3  
<br> 

## Como executar o projeto

### Back end

**Requisitos do Sistema**
- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- [Maven 3.8.1 ou superior](https://maven.apache.org/download.cgi).

**Configuração**
1. Clone o repositório:
    ```sh
    git clone git@github.com:diesantana/app-login.git
    cd app-login/backend
    ```

2. Compile e empacote o projeto usando Maven:
    ```sh
    mvn clean install
    ```

3. Inicie a aplicação:
    ```sh
    mvn spring-boot:run
    ```

A aplicação estará disponível em `http://localhost:8080`.

### Front end

**Requisitos do Sistema**
- [Node.js 14.15.0 ou superior](https://nodejs.org/).
- **Angular CLI 17.3.8:** Certifique-se de ter o Angular CLI instalado globalmente.   
  Você pode instalá-lo executando:
    ```sh
    npm install -g @angular/cli@17.3.8
    ```

**Configuração**
1. Clone o repositório:
    ```sh
    git clone git@github.com:diesantana/app-login.git
    cd app-login/frontend
    ```

2. Instale as dependências:
    ```sh
    npm install
    ```

3. Inicie a aplicação Angular:
    ```sh
    ng serve
    ```

A aplicação estará disponível em `http://localhost:4200`.

**Observações**
- Certifique-se de que o back-end esteja em execução antes de testar a integração com o front-end.
- Você pode modificar as configurações de porta ou outras configurações conforme necessário nos arquivos de configuração do Angular ou do Spring Boot.

## Autor
Diego Santana  
https://www.linkedin.com/in/die-santana/