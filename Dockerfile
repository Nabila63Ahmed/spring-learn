#FROM openjdk:8-jdk-alpine
FROM maven:3.6

WORKDIR /spring-demo

COPY pom.xml .
#RUN mvn install

COPY src .
RUN mvn -f pom.xml clean test package
#RUN mvn compiler:compile
#RUN mvn package

ENTRYPOINT ["java", "-jar", "/target/spring-demo-2.2.6.jar"]
