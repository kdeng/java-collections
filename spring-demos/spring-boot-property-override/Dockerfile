FROM openjdk:11-jre-slim

COPY ./target/*-SNAPSHOT.jar /usr/src/app.jar
COPY app/application.yaml /etc/app/application.yaml

WORKDIR /usr/src/
EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.config.location=classpath:/,file:/etc/app/", "-Dspring.devtools.add-properties=false", "-Dspring.jmx.enabled=false", "-jar", "/usr/src/app.jar"]
