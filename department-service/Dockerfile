# a base image of java runtime
FROM openjdk:17-jdk-alpine

# add a working directory
WORKDIR /app

# copy the jar file into the container
COPY target/department-service-0.0.1-SNAPSHOT.jar department-service.jar

# expose the port
EXPOSE 8083

# run the application
ENTRYPOINT ["java", "-jar", "department-service.jar"]