FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

# Usa a imagem oficial do OpenJDK 17 como imagem base
FROM openjdk:17-jdk-slim

# Exponha a porta em que a sua aplicação Spring Boot será executada (ajuste conforme necessário)
EXPOSE 8080

# Copie o arquivo JAR da sua aplicação para o contêiner
COPY --from=build /target/projetodelivery-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]]