# Desafio Backend - Criptografia (API Transparente)

Este projeto é uma solução para o desafio de **Criptografia** do repositório  
[Backend Br Challenges](https://github.com/backend-br/desafios/blob/master/cryptography/PROBLEM.md).

O objetivo foi criar uma API REST onde campos sensíveis (`userDocument` e `creditCardToken`) são criptografados e descriptografados automaticamente ao serem persistidos no banco de dados, de forma transparente para a regra de negócio da aplicação.

## 🚀 Tecnologias

- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA** (Hibernate)
- **MySQL** (Driver)
- **Maven**

## 💡 Solução

Durante o desenvolvimento deste projeto, o foco principal foi manter a camada de serviço (`UserService`) limpa quanto à segurança dos dados.

### Decisões Arquiteturais

1. **Criptografia Transparente com JPA**  
   Em vez de criptografar os dados manualmente no DTO ou no Service, utilizei o `AttributeConverter` do JPA.  
   Isso permite interceptar o dado exatamente no momento antes de ele ir para o banco e logo após ele voltar, garantindo que a aplicação sempre manipule o dado em texto plano, enquanto o banco armazena apenas o dado cifrado.

2. **Algoritmo AES**  
   Optei pelo algoritmo **AES (Advanced Encryption Standard)** por ser um padrão de mercado robusto e eficiente para criptografia simétrica de dados em repouso.

3. **Desafios com a Chave Secreta**  
   Um ponto de atenção durante a implementação foi a manipulação da `SecretKey`. Enfrentei problemas relacionados à leitura da chave via `application.properties`, onde caracteres invisíveis ou espaços em branco causavam o erro `Invalid AES key length`.  
   A solução foi implementar uma sanitização (`.trim()`) na injeção da chave e garantir que ela seja carregada via variáveis de ambiente/propriedades, evitando chaves *hardcoded* no código-fonte.

## ⚙️ Como Rodar

### Pré-requisitos

- JDK 17 ou superior
- MySQL rodando na porta `3306` (ou ajuste no `application.properties`)

### Configuração

No arquivo `src/main/resources/application.properties`, configure suas credenciais de banco e a chave secreta (deve ter exatamente 16, 24 ou 32 caracteres):

```properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
api.security.secret=SuaChaveSecreta123
```

### Execução
No terminal, execute os comandos:

```bash
# Instalar dependências
./mvnw clean install

# Rodar a aplicação
./mvnw spring-boot:run
```

A API estará disponível em: http://localhost:8081

---

## 📍 Endpoints da API


| Método | Endpoint | Descrição |
| --- | --- | --- |
| POST | /api/v1/user | Cria um novo usuário |
| GET | /api/v1/user/{id} | Busca um usuário por ID |
| GET |	/api/v1/user/searchByDocument | Busca por documento | 
| PUT	| /api/v1/user/{id} |	Atualiza dados de um usuário |
| DELETE | /api/v1/user/{id} | Remove um usuário |

Exemplo de POST:

```json
{
  "userDocument": "123.456.789-00",
  "creditCardToken": "1234 5678 9012 3456",
  "value": 5999
}
```

## 🔒 Verificação da Criptografia
Para validar a eficácia da solução "transparente":

### Via API:

Realize um cadastro. A resposta do JSON mostrará os dados legíveis.

### Via Banco de Dados:

Acesse o MySQL e execute:

```sql
use db_crypto_challenge;
SELECT * FROM users;
```
### Resultado:
Você verá que as colunas `user_document` e `credit_card_token` contêm hashes em Base64, confirmando que os dados estão protegidos no disco.




