# a base image of java runtime
FROM openjdk:17-jdk-alpine

# add a working directory
WORKDIR /app

# copy the jar file into the container
COPY target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar

# expose the port
EXPOSE 9999

# run the application
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]
