# SpringZ - E-Commerce Backend (Spring Boot)

SpringZ is a Spring Boot e-commerce backend demonstrating core concepts: users (customers/providers), categories/subcategories, products, and orders.

It provides:

- Server-rendered CRUD pages (Thymeleaf)
- A JSON REST API under `/api/**` protected with JWT (for Postman)

## Repository contents

- Domain models: `src/main/java/com/Shadows/SpringZ/model`
- Repositories: `src/main/java/com/Shadows/SpringZ/repository`
- Services and controllers: `src/main/java/com/Shadows/SpringZ/service` and `.../controller`
- Thymeleaf templates: `src/main/resources/templates`
- JSON API controllers: `src/main/java/com/Shadows/SpringZ/api`
- Security (JWT): `src/main/java/com/Shadows/SpringZ/security`
- Postman collection: `postman/SpringZ_API.postman_collection.json`

## Quick facts

- Java: 17
- Spring Boot: 3.5.6 (see `pom.xml`)
- Build: Maven (wrapper included)

## Prerequisites

- Java 17+
- MySQL (runtime) — configure credentials in `src/main/resources/application.properties`
- No MySQL required for tests (tests use an in-memory H2 database)

## Configuration

Edit `src/main/resources/application.properties` and set your datasource.

Default (current) configuration uses MySQL and creates the database if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/SpringZ?createDatabaseIfNotExist=true&useSSl=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

JWT configuration (used by `/api/auth/**` and `/api/**`):

```properties
springz.app.jwtSecret=ChangeMeToAStrongSecretKeyAtLeast64BytesLong_0123456789_0123456789
springz.app.jwtExpirationMs=3600000
```

Example alternative configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springz
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

## Run (Windows PowerShell)

From the project root you can run using the Maven wrapper:

```powershell
./mvnw.cmd spring-boot:run
```

Or build and run the jar:

```powershell
./mvnw.cmd -DskipTests package
java -jar target\SpringZ-0.0.1-SNAPSHOT.jar
```

The application listens on port 8080 by default (<http://localhost:8080>) unless overridden in `application.properties`.

## Web pages and controller endpoints

The app includes CRUD pages for Products, Categories, Subcategories, Providers, Customers, Users, and Orders.

Home:

- GET `/` (or `/home`) — navigation page

Examples:

- Products: GET `/all`, GET `/addProduct`, POST `/save`, GET `/edit/{id}`, GET `/delete/{id}`
- Categories: GET `/allCategories`, GET `/addCategory`, POST `/saveCategory`
- Orders: GET `/allOrders`, GET `/addOrders`, POST `/saveOrders`

Pages are in `src/main/resources/templates`.

Note: These MVC endpoints return HTML (and POST endpoints often return a redirect).

## JSON API (JWT) for Postman

The JSON API is under `/api/**` and is protected with JWT.

### 1) Create an account

- POST `/api/auth/signup`

Example body:

```json
{
	"name": "API User",
	"email": "api.user@example.com",
	"password": "secret"
}
```

### 2) Login and get a token

- POST `/api/auth/signin`

Example body:

```json
{
	"email": "api.user@example.com",
	"password": "secret"
}
```

The response contains a `token`. Use it in requests:

```
Authorization: Bearer <token>
```

### 3) Call protected endpoints

Examples:

- GET `/api/products`
- POST `/api/products`
- GET `/api/orders`
- POST `/api/orders`

If you call a protected endpoint without a token, you get `401` JSON.

## Postman

Import the collection:

- `postman/SpringZ_API.postman_collection.json`

Recommended run order:

1. Auth → Signup
2. Auth → Signin (saves `token` into collection variables)
3. Use the other requests (they send `Authorization: Bearer {{token}}`)

## Tests

Run tests with:

```powershell
./mvnw.cmd test
```

Tests use `src/test/resources/application.properties` (H2 in-memory DB), so they do not depend on MySQL.

## Troubleshooting

- If `./mvnw.cmd spring-boot:run` fails, it is usually a database connection issue.
	Check `src/main/resources/application.properties` and ensure MySQL is running and credentials are correct.
- To change the port, set `server.port` in `application.properties`.
- If you get Lombok-related warnings in your IDE, enable annotation processing or install the Lombok plugin.

## Security notes

- `/api/**` is stateless and uses JWT.
- MVC pages are currently permitted without login.
- CSRF is disabled to avoid breaking existing Thymeleaf form posts.

## Contributing

Contributions are welcome. Open an issue or submit a pull request and include steps to reproduce any bug.

## License

See the `LICENSE` file in the repository for license details.
