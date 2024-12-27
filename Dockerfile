# Stage 1 - Build the application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
# create JAR File
RUN mvn clean package -DskipTests

# Stage 2 - Run the JAR File
FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar /app/miniproject1.jar
EXPOSE 8080
CMD ["java", "-jar", "miniproject1.jar"]
