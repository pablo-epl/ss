# Use an official Maven image to build the application
FROM maven:3.9.9-sapmachine-23 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the application source code
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use a smaller Java runtime image to run the application
FROM eclipse-temurin:23-jdk-alpine

# Set the working directory for the runtime container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
