# Use an OpenJDK base image with Maven for building the project
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app

# Copy the Maven configuration files
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use an OpenJDK base image for running the application
FROM openjdk:11-jre-slim
WORKDIR /app

# Install Flyway CLI
RUN apt-get update && \
    apt-get install -y wget && \
    wget -qO /usr/local/bin/flyway https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/8.0.1/flyway-commandline-8.0.1-linux-x64.tar.gz && \
    tar xfz /usr/local/bin/flyway && \
    chmod +x /usr/local/bin/flyway

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/employee-management-api-0.0.5-SNAPSHOT.jar employee-api.jar

# Copy the Flyway configuration files
COPY flyway ./flyway

# Run Flyway repair command
RUN flyway -configFiles=flyway/conf/flyway.conf repair

# Run Flyway migration command
RUN flyway -configFiles=flyway/conf/flyway.conf migrate

# Set the entry point to run the JAR file
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "employee-api.jar"]
