# Use uma imagem base do JDK 17
FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

## Argumento para o perfil Spring
ARG PROFILE=default

# Copie o arquivo JAR para o contêiner
COPY build/libs/MedicalClinicManagement-0.0.1-SNAPSHOT-plain.jar app.jar

# Exponha a porta em que a aplicação será executada
EXPOSE 8080

# Comando para executar a aplicação com o perfil especificado
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=${PROFILE}"]
