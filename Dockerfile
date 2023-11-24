FROM openjdk:17-jdk-alpine

COPY target/SofttekActividadM5-0.0.1-SNAPSHOT.jar java_app.jar

ENTRYPOINT [ "java","-jar","java_app.jar" ]