# Use the official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/user-address-boot-app-0.0.1-SNAPSHOT.jar /app/app.jar

# Optionally, set environment variables
ENV JAVA_OPTS=""

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file with optional JVM arguments
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]