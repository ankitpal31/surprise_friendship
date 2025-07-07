# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set app jar location
ARG JAR_FILE=target/*.jar

# Copy jar file
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
