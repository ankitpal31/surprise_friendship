# ---------- STAGE 1: Build ----------
FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /app

# Copy everything
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# ---------- STAGE 2: Run ----------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
