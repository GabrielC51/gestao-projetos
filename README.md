# Gestão de Projetos

<p>
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/igormarinhoargollo/recipe-app?color=%2304D361">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/igormarinhoargollo/recipe-app">
  
  <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/igormarinhoargollo/recipe-app">
    
  <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">
</p>

Este é um sistema para gerenciamento de projetos, desenvolvido em Java.

---

## Tecnologias Utilizadas:

<img alt="Java" src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white" /> <img alt="Spring" src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=spring&logoColor=white" />

## Entidades e Relações

### Entidades principais:

- **Projeto**
  - Campos: id, nome, descrição, dataEntrega, usuarios, tarefas
  - Relação: um projeto possui várias tarefas; um projeto pode ter vários membros

- **Tarefa**
  - Campos: id, nome, descrição, dataEntrega, projetoId, usuarioId
  - Relação: pertence a um projeto e a um usuario

- **Usuário**
  - Campos: id, nome, email, cargo, projetos, tarefas
  - Relação: um usuario possui várias tarefas; um usuario pode pertencer a vários projetos

---

## Entidades e Relacionamentos

```
+----------------+            +------------------+           +----------------+
|    Projeto     |            |  ProjetoUsuario  |           |    Usuário     |
+----------------+            +------------------+           +----------------+
| id (PK)        |<---------+ | projetoId (FK)   | +-------> | id (PK)        |
| nome           |            | usuarioId (FK)   |           | nome           |
| descrição      |            +------------------+           | email          |
| dataEntrega    |                                           | cargo          |
+----------------+                                           +----------------+
       |                                                            |
       |                                                            |
       | 1:N                                                    1:N |
       |                      +----------------+                    |
       +--------------------> |    Tarefa      | <------------------+
                              +----------------+
                              | id (PK)        |
                              | nome           |
                              | descrição      |
                              | dataEntrega    |
                              | projetoId (FK) |
                              | usuarioId (FK) |
                              +----------------+

(Projeto) 1:N (Tarefa)
(Usuário) 1:N (Tarefa)
(Projeto) N:M (Usuário)
```

## Endpoints

> **Lista de endpoints RESTful:**

Projetos
> | Método | Rota                    | Descrição                               |
> |--------|-------------------------|-----------------------------------------|
> | GET    | /projetos               | Lista todos os projetos                 |
> | POST   | /projetos               | Cria um novo projeto                    |
> | GET    | /projetos/{projetoId}          | Detalha um projeto específico           |
> | PUT    | /projetos/{projetoId}          | Atualiza um projeto                     |
> | DELETE | /projetos/{projetoId}          | Remove um projeto                       |
> | POST   | /projetos/{projetoId}/usuarios | Adiciona um usuário a um projeto        |
> | DELETE | /projetos/{projetoId}/usuarios/{usuarioId} | Remove usuário de um projeto  |

Tarefas
> | Método | Rota                    | Descrição                               |
> |--------|-------------------------|-----------------------------------------|
> | GET    | /tarefas                | Lista todas as tarefas                  |
> | POST   | /tarefas                | Cria uma nova tarefa                    |
> | GET    | /tarefas/{tarefaId}           | Detalha uma tarefa específica           |
> | PUT    | /tarefas/{tarefaId}           | Atualiza uma tarefa                     |
> | DELETE | /tarefas/{tarefaId}           | Remove uma tarefa                       |

Usuarios
> | Método | Rota                    | Descrição                               |
> |--------|-------------------------|-----------------------------------------|
> | GET    | /usuarios               | Lista todos os usuários                 |
> | POST   | /usuarios               | Cria um novo usuário                    |
> | GET    | /usuarios/{id}          | Detalha um usuário específico           |
> | PUT    | /usuarios/{id}          | Atualiza um usuário                     |
> | DELETE | /usuarios/{id}          | Remove um usuário                       |

Autenticação
> | Método | Rota                    | Descrição                               |
> |--------|-------------------------|-----------------------------------------|
> | POST   | /auth/login             | Rota para Autenticação do usuario       |

---

## Requisitos para Rodar o Sistema

- **Java** (JDK 21 ou superior)
- **Maven** para gerenciamento de dependências
- (Opcional) IDE como IntelliJ IDEA ou Eclipse

### Passos para rodar:

1. Clone o repositório:
```bash
git clone https://github.com/GabrielC51/gestao-projetos.git
```

2. Importe o projeto para sua IDE Java.
   
3. Configure o arquivo em que voce quer salvar o banco de dados em `application.properties`.
   
4. Passos para rodar:

   4.1. Rodando no IntelliJ: Abra o arquivo GestaoprojetosApplication e clique para rodar a aplicação

   4.2. Rodando em outra IDE:
   
      * 4.2.1. Instale as dependências:
   
      ```bash
      mvn install
      ```
      
      * 4.2.2. Execute a aplicação:
   
      ```bash
      mvn spring-boot:run
      ```
     
5. Acesse a aplicação conforme instruções do projeto.

   5.1. Para acessar as rotas protegidas (/projetos e /tarefas) o usuario precisa ter o cargo "ADMIN".

   5.2. é possivel realizar os testes em /swagger-ui/index.html
---

## Contribuidores

<table>
  <tr>
    <td align="center"><a href="https://github.com/GabrielC51"><img src="https://avatars.githubusercontent.com/u/74744767?v=4" width="80" alt="GabrielC51"/><br/>Gabriel Campos</a></td>
    <td align="center"><a href="https://github.com/IgorMarinhoArgollo"><img src="https://avatars.githubusercontent.com/u/85767736?v=4" width="80" alt="IgorMarinhoArgollo"/><br/>Igor Marinho Argollo</a></td>
    <td align="center"><a href="https://github.com/MaxorV"><img src="https://avatars.githubusercontent.com/u/45106245?v=4" width="80" alt="MaxorV"/><br/>Anderson Carvalho</a></td>
  </tr>
</table>

---

## Licença

  <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen"><br><br>
