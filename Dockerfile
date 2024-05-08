FROM openjdk:17-alpine

VOLUME /tmp

COPY target/travelmap-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "/app.jar"]