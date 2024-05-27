# OrderService/Dockerfile
FROM openjdk:17
EXPOSE 8084
COPY target/OrderService-0.0.1-SNAPSHOT.jar OrderService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/OrderService-0.0.1-SNAPSHOT.jar"]