# ConfigServer/Dockerfile
FROM openjdk:17
EXPOSE 8888
COPY target/ConfigServer-0.0.1-SNAPSHOT.jar ConfigServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ConfigServer-0.0.1-SNAPSHOT.jar"]