FROM openjdk:11
ADD target/healthCheckApplication-0.0.1-SNAPSHOT.jar healthcheckapp.jar
ENTRYPOINT ["java" ,"-jar", "healthcheckapp.jar"]