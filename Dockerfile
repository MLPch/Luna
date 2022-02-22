FROM openjdk:17.0.2

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY Luna.jar Luna.jar

ENTRYPOINT ["/bin/sh", "-c", "java -jar Luna.jar"]