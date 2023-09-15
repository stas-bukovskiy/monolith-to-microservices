FROM openjdk:17
ENV SPRING_PROFILES_ACTIVE docker
ENV SERVER_PORT 8002
VOLUME /tmp
EXPOSE 8002
ARG JAR_FILE=/target/warehouse-service-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]