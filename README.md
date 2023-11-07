# Projeto de Rede Social com Spring Boot

<div>
  <img src="https://img.shields.io/github/stars/tav1nnn/rede-social.svg" />
</div>

#

<div>
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" target="_blank"/>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" target="_blank"/>
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" target="_blank"/>
  <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" target="_blank"/>
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white" target="_blank"/>
  
          
</div>
                        
## Descrição do Projeto
Este é um projeto pessoal que visa criar uma API de rede social de publicações, utilizando o poderoso framework Spring Boot. A ideia é desenvolver uma plataforma que permita aos usuários compartilhar suas experiências por meio de publicações, interagir com outras pessoas e explorar um ambiente social dinâmico.

## Funcionalidades a serem desenvolvidas
O sistema contará com uma variedade de funcionalidades, tornando a experiência do usuário rica e envolvente:
  
- 📣 **Notificações**: Os usuários receberão notificações sobre atividades relevantes, como curtidas e comentários em suas publicações.
  
- 👥 **Seguidores**: Os usuários podem seguir outros usuários para acompanhar suas atualizações e interagir com suas publicações.
  
- 🔍 **Pesquisa com Filtros**: Uma funcionalidade de pesquisa avançada permitirá aos usuários encontrar publicações específicas ou outros usuários com base em diferentes critérios.
  
- 📄 **Feed de Publicações**: Os usuários terão acesso a um feed personalizado, exibindo as publicações de quem eles seguem.
  
- 📚 **Swagger**: Utilizaremos o Swagger para documentar e facilitar o consumo da API, tornando-a mais acessível e amigável.
  
- 🧪 **Testes**: Implementaremos testes automatizados para garantir a confiabilidade e a qualidade do sistema.

## Funcionalidades já desenvolvidas
Até o momento,  já foi desenvolvido:

- ✅ **Cadastro**: Os usuários podem criar suas contas no sistema.
  
- 🔐 **Login com Autenticação JWT**: A autenticação JWT (JSON Web Tokens) foi implementada para garantir a segurança das contas de usuário.
  
- 📝 **Nova Publicação**: Os usuários podem criar e compartilhar novas publicações.
  
- 💖 **Curtidas**: Os usuários podem expressar seu apreço por publicações curtindo-as.
  
- 🔄 **Compartilhamento**: As publicações podem ser compartilhadas, ampliando sua visibilidade para outros usuários.
  
- 💬 **Comentários**: Os usuários têm a capacidade de comentar nas publicações, permitindo discussões e interações significativas.


## Configurações e Recursos Adicionais

- 🌐 **Banco de Dados MySQL**: Armazenamos os dados do sistema em um banco de dados MySQL

- 📜 **Flyway**: Utilizamos o Flyway para gerenciar e versionar as migrações do banco de dados.

- 🔐 **Spring Security Crypto**: O Spring Security Crypto é empregado para criptografar senhas e dados sensíveis, aumentando a segurança do sistema.

- ✅ **Bean Validation**: Usamos a validação de beans para garantir que os dados fornecidos pelos usuários atendam aos critérios de validação definidos.

- 🔒 **Auth0 JWT**: A autenticação JWT fornecida pela Auth0 é integrada ao sistema para garantir a segurança das informações e a autenticação de usuários.


## Pré-requisitos

Antes de começar, certifique-se de que você tenha o seguinte software instalado em seu sistema:

- Java Development Kit (JDK) 17.
- Apache Maven
- Git (opcional, se você quiser clonar o repositório)

## Clone o Repositório
Se você tem o Git instalado, você pode clonar este repositório com o seguinte comando:

```bash
git clone https://github.com/Tav1nnn/rede-social
```

## Inicializando a Aplicação

1. Acesse o diretório pelo terminal
Exemplo:
```bash
cd meus-arquivos/rede-social
```

2. Rode este comendo para construir o projeto Maven
```bash
mvn clean install
```

3. Inicie o projeto
```bash
mvn spring-boot:run
```

4. Acesse o projeto pela porta padrão `http://localhost:8080`

