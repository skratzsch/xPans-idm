FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} xpans-idm.jar
ENTRYPOINT ["java","-jar","/xPans-idm-1.0-SNAPSHOT.jar"]