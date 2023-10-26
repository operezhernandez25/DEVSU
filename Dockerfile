# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Copiar el archivo jar de tu aplicación al contenedor
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Configurar el punto de entrada para tu aplicación
ENTRYPOINT ["java","-jar","/app.jar"]
