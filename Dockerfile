FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar sport_shop.jar
ENTRYPOINT ["java","-jar","/sport_shop.jar"]