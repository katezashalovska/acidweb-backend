# Dockerfile for AcidSoft Java Spring Boot Backend

# Stage 1: Build stage
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# Copy wrapper and Gradle configuration
COPY gradlew gradlew.bat settings.gradle.kts build.gradle.kts ./
COPY gradle gradle

# Copy source code
COPY src src

# Make wrapper executable and build bootJar
RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon -x test

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create non-root system user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
