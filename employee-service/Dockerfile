# a base image of java runtime
FROM openjdk:17-jdk-alpine

# add a working directory
WORKDIR /app

# copy the jar file into the container
COPY target/employee-service-0.0.1-SNAPSHOT.jar employee-service.jar

# expose the port
EXPOSE 8081

# run the application
ENTRYPOINT ["java", "-jar", "employee-service.jar"]