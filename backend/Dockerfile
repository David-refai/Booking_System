# Use the official OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/bookingdb
ENV SPRING_DATASOURCE_USERNAME=david
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver

# Set working directory
WORKDIR /app

# Copy the compiled JAR file to the container
COPY target/booking-system-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar"]
