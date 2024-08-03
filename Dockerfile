FROM eclipse-temurin:17

LABEL author="ganapp"

# Previamente realizar un mvn clean package
COPY target/GanApp-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]

