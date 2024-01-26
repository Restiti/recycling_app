FROM openjdk:17
LABEL authors="remy"
ADD target/springboot-docker-compose.jar springboot-docker-compose.jar
ENTRYPOINT ["java", "-jar", "springboot-docker-compose.jar"]