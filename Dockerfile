FROM gradle:jdk17 AS builder

WORKDIR /app
COPY . .

RUN gradle clean build

FROM openjdk:17

WORKDIR /app
COPY --from=builder /app/build/libs/Zwash-0.0.1.jar .

ENTRYPOINT ["java", "-jar", "Zwash-0.0.1.jar"]
EXPOSE 8080
EXPOSE 7001
