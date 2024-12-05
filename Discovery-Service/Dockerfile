FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/discovery-service-0.0.1-SNAPSHOT.jar
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]