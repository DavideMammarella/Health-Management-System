FROM openjdk:8-jdk-alpine
COPY ./target/*.jar /app.jar
# "-Djava.security.egd=file:/dev/./urandom" optimize tomcat startup
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080