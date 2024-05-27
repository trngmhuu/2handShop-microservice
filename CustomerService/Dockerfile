# CustomerService/Dockerfile
FROM openjdk:17
EXPOSE 8083
COPY target/CustomerService-0.0.1-SNAPSHOT.jar CustomerService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/CustomerService-0.0.1-SNAPSHOT.jar"]