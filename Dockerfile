FROM openjdk:17
LABEL authors="remy"
ADD ./target/springboot-api.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
