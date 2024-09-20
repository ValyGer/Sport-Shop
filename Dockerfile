FROM maven:4.0.0-autocorrection:17 AS build
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src
RUN mvn package -DskipTests

FROM openjdk: 17-alpine
ARG JAR_FILE=/build/target/*.jar
COPY --from=bildds $JAR_FILE /opt/docker-test/sport_shop.jar
ENTRYPOINT ["java","-jar","/opt/docker-test/sport_shop.jar"]