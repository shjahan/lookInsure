# **Insurance Quote Management Service**

A Spring Boot application for managing insurance quotes from different providers.

## **Features**

- CRUD operations for insurance quotes
- Quote aggregation with filtering and sorting
- Caching implementation using Redis
- Postgres database
- Liquibase Database integration
- Input validation
- Comprehensive error handling

## **Requirements**

- Java 17 or higher
- Maven 3.6 or higher
- Docker

## **Tech Stack**

- Java 17
- Spring Boot 3
- Postgres
- Hibernate
- JPA
- Liquibase
- Docker
- Docker-Compose
- Redis cache


## **Postgres Database**

Connection details:

```jsx
url: jdbc:postgresql://localhost:5432/quotes
username: quotes
password: quotes
```

## **Caching**

The application implements caching using Redis with the following configuration:

- Maximum cache size: 100 entries
- Cache expiration: 15 minutes after write
- Cached endpoints:
  - Individual quote retrieval
  - Quote aggregation results


## **Getting Started**

1- Clone the repository:

```bash
git clone https://github.com/shjahan/lookInsure.git
cd quotes

```

2- Build and run the project:

```bash
docker-compose up -d
```

Enjoy It! The application will start on [http://localhost:8080](http://localhost:8080/)

## **API Endpoints**

### **Quotes Management**

- `GET /quotes/{id}` - Retrieve a specific quote
- `POST /quotes` - Create a new quote
- `PUT /quotes/{id}` - Update an existing quote
- `DELETE /quotes/{id}` - Delete a quote
- `GET /quotes` - List all quotes
- `GET /quotes/aggregate` - Get aggregated quotes with filtering and sorting
- `GET /providers`  - List all providers

### **Api Requests**

Retrieve a specific quote

```jsx
GET http://localhost:8080/quotes/1
```


Create a new quote:

```jsx
POST http://localhost:8080/quotes
        Content-Type: application/json

{
  "coverageType": "HEALTH",
        "price": 10,
        "providerId": 4,
        "deductibleAmount": 999,
        "coverageLimit": 99,
        "terms": "no condition"
}
```

Get aggregated quotes:

```jsx
GET http://localhost:8080/quotes/aggregate?coverageType=BUSINESS&sortBy=best
```
List all quotes

```jsx
GET http://localhost:8080/quotes
```

Update an existing quote
```jsx
PUT http://localhost:8080/quotes/{{id}}
Content-Type: application/json

{
  "id": 0,
  "coverageType": "HEALTH",
  "providerId": 0,
  "price": 0,
  "deductibleAmount": 0,
  "coverageLimit": 0,
  "terms": ""
}
```
Delete a quote
```jsx
DELETE http://localhost:8080/quotes/{{id}}
```
List all providers
```jsx
GET http://localhost:8080/provider
```
## Note:

if you want run code on Intellij please uncomment below line in maven. its happen when lombok and spring not
compatible.
POM file, line 97
```jsx
<!--<compilerArgument>-proc:none</compilerArgument>-->
```

## **Authors**

- [@shjahan](https://github.com/shjahan)

## **License**

This project is licensed under the MIT License - see the LICENSE file for details.