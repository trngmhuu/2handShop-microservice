# OrderItemService/Dockerfile
FROM openjdk:17
EXPOSE 8085
COPY target/OrderItemsService-0.0.1-SNAPSHOT.jar OrderItemsService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/OrderItemsService-0.0.1-SNAPSHOT.jar"]