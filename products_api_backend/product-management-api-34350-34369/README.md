# product-management-api-34350-34369

Products API Backend (Spring Boot)

- Framework: Spring Boot 3 (Java 17)
- Storage: In-memory H2 with seed data
- API Docs: OpenAPI/Swagger UI at /docs (redirects to /swagger-ui.html)
- Health: /health

Getting Started
1) Navigate into the backend:
   cd products_api_backend

2) Run the app:
   ./gradlew bootRun

The service is configured for the existing container port 3001.

Service URLs (once running)
- Docs: https://<host>:3001/docs
- OpenAPI JSON: https://<host>:3001/api-docs
- Health: https://<host>:3001/health
- H2 Console: https://<host>:3001/h2-console (JDBC: jdbc:h2:mem:testdb, user: sa, password: empty)

Endpoints
- GET /products — List all products
- GET /products/{id} — Get a product by id
- POST /products — Create a product
- PUT /products/{id} — Update a product
- DELETE /products/{id} — Delete a product

Validation and Errors
- 400 Bad Request: Validation errors with a fieldErrors map.
- 404 Not Found: When a product with the given id does not exist.
- 500 Internal Server Error: Generic error handler with a helpful message.

Sample Seed Data (loaded at startup)
- Wireless Mouse (price 19.99, qty 50)
- Mechanical Keyboard (price 89.90, qty 20)
- USB-C Cable (price 7.49, qty 150)

Example curl Commands
List products:
curl -s https://<host>:3001/products

Get product by id:
curl -s https://<host>:3001/products/1

Create a product:
curl -s -X POST https://<host>:3001/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop Stand","price":29.99,"quantity":30}'

Update a product:
curl -s -X PUT https://<host>:3001/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Wireless Mouse Pro","price":24.99,"quantity":60}'

Delete a product:
curl -i -s -X DELETE https://<host>:3001/products/1

Notes
- Use /docs for the Swagger UI with the Ocean Professional style of documentation structure.
- The app respects forwarded headers to ensure proper redirects when behind proxies.
