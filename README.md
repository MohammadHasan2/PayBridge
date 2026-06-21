# PayBridge

## Overview

PayBridge is a Spring Boot payment gateway platform that enables merchants to process payments, calculate VAT automatically based on country, generate invoices, and access business reports through secure authenticated APIs.

The project is designed as a production-style backend system and demonstrates authentication, authorization, payment processing, transaction management, invoice generation, API documentation, containerization, and secure backend practices.

---

## Features

### Authentication & Security

* User registration
* User login
* JWT-based authentication
* BCrypt password hashing
* Protected endpoints
* Stateless security configuration

### Merchant Management

* Merchant registration
* Merchant ownership linked to authenticated users
* Country-based VAT configuration

## Reliability Features

* JWT-based authentication
* Password hashing with BCrypt
* Protected endpoints
* Idempotent Stripe webhook processing
* Docker containerization
* OpenAPI / Swagger documentation
* PostgreSQL persistence

### Payment Processing

* Payment creation endpoint
* Automatic VAT calculation
* Platform fee calculation
* Merchant revenue calculation
* Transaction persistence

### Reporting

* Merchant-specific reports
* Total processed payments
* Total VAT collected
* Total platform fees
* Total merchant revenue

### Invoice Management

* Automatic invoice creation
* Unique invoice numbers
* Invoice retrieval endpoint
* PDF invoice generation

### Stripe Integration

* Stripe Test Mode integration
* Webhook endpoint handling
* Payment success processing
* Transaction creation from webhook events
* Idempotent webhook processing to prevent duplicate transactions

### Production Readiness

* Docker support
* Global API structure
* Layered architecture
* OpenAPI / Swagger documentation
* PostgreSQL database integration
* Supabase PostgreSQL support

---

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL
* Supabase

### Authentication

* JWT (JSON Web Tokens)
* BCrypt Password Encoder

### Payment Integration

* Stripe Test Mode
* Stripe Webhooks

### Documentation

* Swagger / OpenAPI

### Deployment

* Docker
* Docker Compose

---

## Architecture

```text
Client
   ↓
Controllers
   ↓
Services
   ↓
Repositories
   ↓
PostgreSQL
```

### Layers

#### Controllers

Handle incoming HTTP requests.

Examples:

* AuthController
* MerchantController
* PaymentController
* ReportController
* InvoiceController

#### Services

Contain business logic.

Examples:

* UserService
* MerchantService
* PaymentService
* ReportService
* StripeWebhookService
* VatService

#### Repositories

Provide database access using Spring Data JPA.

Examples:

* UserRepository
* MerchantRepository
* TransactionRepository
* InvoiceRepository

---

## Authentication Flow

### Register

```http
POST /auth/register
```

Creates a new user account.

### Login

```http
POST /auth/login
```

Returns a JWT token.

### Authorized Requests

```http
Authorization: Bearer <JWT_TOKEN>
```

Used to access protected endpoints.

---

## Main API Endpoints

### Authentication

```http
POST /auth/register
POST /auth/login
```

### Merchants

```http
POST /api/merchant/register
```

### Payments

```http
POST /api/payments
```

### Reports

```http
GET /api/reports/me
```

### Invoices

```http
GET /api/invoices/{id}
```

### Health Check

```http
GET /health
```

---

## VAT Calculation

VAT is automatically determined based on merchant country.

Example rates:

| Country | VAT |
| ------- | --- |
| Lebanon | 11% |
| France  | 20% |
| Germany | 19% |
| UAE     | 5%  |

Default VAT rate applies when a country is not configured.

---

## Stripe Webhook Flow

```text
Stripe Payment
       ↓
Webhook Event
       ↓
StripeWebhookController
       ↓
StripeWebhookService
       ↓
Create Transaction
       ↓
Create Invoice
       ↓
Generate PDF
```

---

## API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

---

## Running Locally

### Clone Repository

```bash
git clone https://github.com/MohammadHasan2/PayBridge.git
```

### Build Project

```bash
mvn clean package
```

### Run Application

```bash
mvn spring-boot:run
```

---

## Docker

### Build Image

```bash
docker build -t paybridge .
```

### Run Container

```bash
docker run -p 8080:8080 paybridge
```

---

## Future Improvements

* Merchant API keys
* Advanced webhook reliability
* Audit logging
* Email delivery service
* Monitoring & metrics
* Event-driven architecture
* Message queues (RabbitMQ/Kafka)

---

## Author

PayBridge was built as a backend-focused fintech project demonstrating secure payment processing, merchant onboarding, VAT calculation, invoice generation, reporting, and production-style backend architecture using Spring Boot.
