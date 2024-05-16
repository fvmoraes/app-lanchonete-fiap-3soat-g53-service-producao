# Sistema de Pedidos para Lanchonete - FIAP 3SOAT (Grupo 53)
---
---
## Estrutura e tecnologias utilizadas
### Qual a proposta?
>Este projeto é um microserviço para a aplicação de uma lanchonete, desenvolvida como parte do curso de pós graduação em Software Architeture da FIAP. É encarregado da parte de produção dos pedidos.

Os microserviços fazem a comunicação entre si utilizando o RabbitMQ


### Tecnologias utilizadas:
- [Java](https://dev.java/learn/)
- [Docker](https://docs.docker.com/get-started/)
- [Cassandra](https://cassandra.apache.org/_/index.html)
- [PostgreSQL](https://www.postgresql.org/about/)
- [PGAdmin](https://www.pgadmin.org/docs/)
- [Swagger](https://swagger.io/solutions/api-documentation/)

### Informações Básicas:

 Para interação básica, você pode usar o Swagger (via navegador) ou o Postman (importando a collection, o arquivo FIAP.postman_collection.json que contém a configuração essencial para iniciar o uso).

 O banco de dados Cassandra e o banco de dados Postgress já estão configuradosdentro do docker-compose prontos para uso  dos três microserviços

 O projeto possui boas praticas de programação com base em DDD e CleanCode.

### banco de Dados

Como o Cassandra é um banco de dados NoSQL orientado a colunas e não segue o modelo relacional tradicional, não é comum "desenhar" uma tabela da mesma forma que faríamos em um banco de dados relacional.
Esté é apenas um esboço conceitual do esquema de colunas para a tabela pedido com base no modelo de dados:

Tabela: pedido

+-------------------+-------------------+-------------------+-------------------+---------------------+

|-id-(primary key)--|-lista_produtos-----|-status_pedido-----|-valor_total------|-data_criacao--------|

+-------------------+-------------------+-------------------+-------------------+---------------------+

|-1-----------------|-"produto1,-produto2,-produto3"-|-"PENDENTE"-|-30.00---|-2024-05-16 10:30:00-|

|-2-----------------|-"produto4,-produto5"-----------|-"CONCLUÍDO"-|-20.00-|-2024-05-16 11:45:00--|

+-------------------+-------------------+-------------------+-------------------+---------------------+

Neste esquema conceitual:

    id: É a chave primária da tabela, usada para identificar exclusivamente cada pedido.
    lista_produtos: Armazena a lista de produtos do pedido como uma string. Você pode optar por serializar os dados dos produtos como uma string, separada por vírgulas, ou pode normalizar essa relação em uma tabela de produtos separada, dependendo da sua necessidade e complexidade do sistema.
    status_pedido: Armazena o status do pedido como uma string. Aqui, por exemplo, usei "PENDENTE" e "CONCLUÍDO", mas você pode ter outros estados conforme necessário.
    valor_total: Armazena o valor total do pedido como um decimal.
    data_criacao: Armazena a data e hora de criação do pedido como um timestamp.

Este esboço é uma representação simplificada do modelo de dados em um banco de dados NoSQL orientado a colunas como o Cassandra.

## Como Usar
### O que preciso ter instalado no meu computador?
> Qualquer sistema operacional Linux ou subsistema Linux, Windows ou MacOs

> Docker, Docker Desktop e Docker Compose instalados na sua máquina

- [Docker](https://docs.docker.com/get-started/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Como executar o projeto no meu computador?
> Não é necessario criar databases, ou rodar o projeto de forma local, e sim apenas utilizar um docker-compose que existe neste repositorio. Esse Docker-compose já está configurado para rodar os três microserviços.

> Dentro do repositorio da aplicação, o comando inicial pode ser o seguinte para uso com Docker Compose:
```sh
docker-compose up
``````
> Após é só rodar localmente o projeto!



---
## Mais informações sobre a API
### Lista de endpoints

GET http://localhost:8080/api/v1/producao/pedido

POST http://localhost:8080/api/v1/producao/pedido

POST http://localhost:8080/api/v1/producao/pedido/{id}/{status}

GET http://localhost:8080/api/v1/producao/pedido/{id}

GET http://localhost:8080/api/v1/producao/pedido/status/{status}

GET http://localhost:8080/api/v1/producao/pedido/proximo

> Swagger
- GET http://localhost:8080/swagger-ui/index.html
---
---
## Referências
### Este projeto foi criado usando os seguintes pacotes Java
- [spring boot starter data jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [spring boot starter web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [springdoc openapi starter webmvc ui](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui)
- [postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql)
- [hibernate validator](https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator)
- [spring boot](https://spring.io/projects/spring-boot/)

---
---
_fim do README.md_
