FROM openjdk:17
COPY target/spring-bank-BE-0.0.1-SNAPSHOT.jar spring-bank-BE.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-bank-BE.jar"]