# Usar una imagen base con JDK 17
FROM amazoncorretto:17-alpine-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /root

# Copiar el archivo JAR generado por Maven al contenedor
COPY target/alfashop-1.0.jar app.jar

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
