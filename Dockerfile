FROM eclipse-temurin:17

LABEL author="codifacil.club"

ENV DATABASE_URL=jdbc:mysql://127.0.0.1:3306/codibdfacil
ENV DATABASE_USERNAME=codi_user
ENV DATABASE_PASSWORD=UA2UV6CM4CAmWTS
ENV DATABASE_PLATFORM=org.hibernate.dialect.MySQL57Dialect
ENV DATABASE_DRIVER=com.mysql.cj.jdbc.Driver

# Previamente realizar un mvn clean package
COPY target/codifacil-backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que tu aplicación usará
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

# Para construir la imagen:
# docker build -t codifacil-backend-image:1 .
# Para correr el contenedor:
# docker run -p 8081:8081 --name codifacil-backend-container codifacil-backend-image:1