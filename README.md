# ProjetoBackendJava

<img src="videoApiProduto.gif" width=700>

## Descrição

Projeto de API RESTful desenvolvida em Java com Spring Boot, utilizando JPA para persistência de dados e PostgreSQL como banco de dados. A API gerencia produtos, permitindo operações de cadastro, consulta, atualização e remoção.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.2
- Spring Data JPA
- PostgreSQL
- ModelMapper
- Springdoc OpenAPI (Swagger)
- Maven

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/ProjetoBackendJava.git
   ```

2. **Configure o banco de dados:**

- Altere as propriedades em `src/main/resources/application.properties` conforme seu ambiente:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/mercadinho
spring.datasource.username=postgres
spring.datasource.password=123456

```

3. Instale as dependências e execute o projeto:

- `mvn spring-boot:run`

4. Acesse a documentação Swagger:
- http://localhost:8083/swagger-ui.html

## Endpoints Principais

| Método | Endpoint           | Descrição                     |
|--------|--------------------|-------------------------------|
| GET    | /api/produtos      | Lista todos os produtos       |
| GET    | /api/produtos/{id} | Busca produto por ID          |
| POST   | /api/produtos      | Cadastra um novo produto      |
| PUT    | /api/produtos/{id} | Atualiza um produto existente |
| DELETE | /api/produtos/{id} | Remove um produto pelo ID     |

## Exemplo de Requisição (JSON)
- POST/api/produtos

```
{
  "nome": "Notebook",
  "quantidade": 10,
  "valor": 3500.00,
  "observacao": "Produto novo"
}
```

## Observações
O projeto utiliza ModelMapper para conversão entre entidades e DTOs.
O Springdoc OpenAPI gera automaticamente a documentação interativa da API.
Certifique-se de que o banco de dados PostgreSQL esteja rodando antes de iniciar a aplicação.