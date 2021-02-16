FROM openjdk:11
ADD target/grades-service-0.0.1-SNAPSHOT.jar grades-service.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "grades-service.jar"]