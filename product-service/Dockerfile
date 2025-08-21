FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} product-service.jar
ENTRYPOINT ["java","-jar","product-service.jar"]