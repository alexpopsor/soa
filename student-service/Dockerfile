FROM openjdk:11
ADD target/student-service-0.0.1-SNAPSHOT.jar student-service.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "student-service.jar"]