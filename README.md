# SpringZ - E-Commerce Backend

SpringZ is a Spring Boot e-commerce backend for users, customers, providers, categories, subcategories, products, and orders.

It now provides:

- A JSON REST API under `/api/**`
- JWT authentication for protected API requests
- CORS support for an Angular frontend running on `http://localhost:4200`
- A Postman collection for API testing

## Repository Contents

- Domain models: `src/main/java/com/Shadows/SpringZ/model`
- Repositories: `src/main/java/com/Shadows/SpringZ/repository`
- Services: `src/main/java/com/Shadows/SpringZ/service`
- JSON API controllers: `src/main/java/com/Shadows/SpringZ/api`
- Security: `src/main/java/com/Shadows/SpringZ/security`
- Postman collection: `postman/SpringZ_API.postman_collection.json`

The previous Thymeleaf MVC controllers and templates were removed. The UI should be handled by Angular.

## Prerequisites

- Java 17+
- MySQL for runtime
- Maven wrapper included in this repository

Tests use an in-memory H2 database, so MySQL is not required for tests.

## Backend Configuration

Edit `src/main/resources/application.properties` if your database settings are different.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce?createDatabaseIfNotExist=true&useSSl=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

JWT configuration:

```properties
springz.app.jwtSecret=ChangeMeToAStrongSecretKeyAtLeast64BytesLong_0123456789_0123456789
springz.app.jwtExpirationMs=3600000
```

## Run The Backend

From the Spring project root:

```powershell
.\mvnw.cmd spring-boot:run
```

The API runs at:

```text
http://localhost:8081
```

## Angular Frontend

Run the Angular app separately on:

```text
http://localhost:4200
```

Use this API base URL in Angular:

```text
http://localhost:8081/api
```

The backend allows CORS from:

- `http://localhost:4200`
- `http://127.0.0.1:4200`

## Authentication

Create an account:

```http
POST /api/auth/signup
```

```json
{
  "name": "API User",
  "email": "api.user@example.com",
  "password": "secret"
}
```

Sign in:

```http
POST /api/auth/signin
```

```json
{
  "email": "api.user@example.com",
  "password": "secret"
}
```

The response contains a JWT token. Send it on protected requests:

```http
Authorization: Bearer <token>
```

## API Examples

- `GET /api/products`
- `POST /api/products`
- `GET /api/categories`
- `GET /api/orders`

Protected API requests without a valid token return `401`.

## Postman

Import:

```text
postman/SpringZ_API.postman_collection.json
```

Recommended run order:

1. `Auth -> Signup`
2. `Auth -> Signin`
3. Use the other API requests with `Authorization: Bearer {{token}}`

## Tests

Run:

```powershell
.\mvnw.cmd test
```
