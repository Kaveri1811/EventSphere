# Use Java 21 runtime
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/*.jar app.jar

# Expose application port
EXPOSE 8888

# Start the application
ENTRYPOINT ["java","-jar","app.jar"]