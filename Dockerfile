FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/
ENTRYPOINT ["java", "-jar", "java-todo-api-1.0.0.jar"]