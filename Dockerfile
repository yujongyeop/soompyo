# Build stage
FROM openjdk:17 as builder
WORKDIR /app
COPY build/libs/*.jar app.jar

# Runtime stage (lightweight)
FROM gcr.io/distroless/java17
WORKDIR /app
COPY --from=builder /app/app.jar app.jar
COPY .env .env

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar","-Dspring.profiles.active=real"]