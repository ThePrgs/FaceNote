# Use a base image with Java 21 and Maven installed
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project
COPY . .

# Package the application
RUN mvn clean package -DskipTests

# Runtime image (lighter than build image)
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the JAR from the build image
COPY --from=build /app/target/FaceNoteBackend-0.0.1-SNAPSHOT.jar app.jar

# Run the app
CMD ["java", "-jar", "app.jar"]
