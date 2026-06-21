# Build stage - compiles the Java application
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /build

# Copy Maven configuration
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# Runtime stage - runs the application
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /build/target/paybridge-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
