# Stripe Payment Integration

A Spring Boot application for handling Stripe payments with webhook processing.

## 🔒 Security Setup

### Before Running Locally:

1. **Copy configuration template:**
   ```bash
   cp application.properties.example src/main/resources/application.properties
   ```

2. **Fill in your credentials:**
   ```properties
   # Edit src/main/resources/application.properties
   spring.datasource.url=YOUR_DATABASE_URL
   spring.datasource.username=YOUR_DB_USER
   spring.datasource.password=YOUR_DB_PASSWORD
   stripe.secret-key=YOUR_STRIPE_SECRET_KEY
   stripe.webhook.secret=YOUR_STRIPE_WEBHOOK_SECRET
   ```

3. **Never commit `application.properties`** - It's in `.gitignore`

### Environment Variables (Alternative - Recommended for Production)

Set environment variables instead of using properties file:

```bash
# Linux/Mac
export SPRING_DATASOURCE_URL=jdbc:postgresql://...
export SPRING_DATASOURCE_USERNAME=username
export SPRING_DATASOURCE_PASSWORD=password
export STRIPE_SECRET_KEY=sk_test_...
export STRIPE_WEBHOOK_SECRET=whsec_...
```

```cmd
# Windows
set SPRING_DATASOURCE_URL=jdbc:postgresql://...
set SPRING_DATASOURCE_USERNAME=username
set SPRING_DATASOURCE_PASSWORD=password
set STRIPE_SECRET_KEY=sk_test_...
set STRIPE_WEBHOOK_SECRET=whsec_...
```

## 📦 Build & Run

```bash
# Build
./mvnw clean package

# Run
./mvnw spring-boot:run
```

## ⚠️ What NOT to Commit

- `src/main/resources/application.properties` - Contains credentials
- `logs/` - Runtime logs
- `target/` - Build artifacts
- `.idea/`, `.vscode/` - IDE files
- Any `.env` files

## ✅ What to Commit

- All source code in `src/`
- `pom.xml`
- `application.properties.example` - Template only
- Documentation files
- Test files

## 🔑 Stripe Configuration

1. Get your keys from [Stripe Dashboard](https://dashboard.stripe.com)
2. Set webhook secret from Stripe Dashboard → Webhooks
3. Configure webhook URL to point to `/api/stripe/webhook`

## 🚀 Deployment Notes

Never commit credentials to GitHub. Use:
- GitHub Secrets for CI/CD
- Environment variables on servers
- Cloud provider secret management (AWS Secrets Manager, etc.)

