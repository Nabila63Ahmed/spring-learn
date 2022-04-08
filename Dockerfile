FROM gradle:7.4.0-jdk11

WORKDIR /spring-demo

COPY . .
RUN gradle build -x test --no-daemon

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/spring-demo/build/libs/spring-demo-0.0.1-SNAPSHOT.jar"]
