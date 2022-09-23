FROM openjdk:8-jdk-alpine
MAINTAINER indiagator
COPY target/test-service-4-fetchuserinfo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app.jar"]