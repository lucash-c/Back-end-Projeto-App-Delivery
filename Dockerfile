# Use a imagem oficial do OpenJDK 17 como imagem base
FROM openjdk:17-jdk-slim

# Define um diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação para o contêiner
COPY target/projetodelivery-0.0.1-SNAPSHOT.jar projetodelivery.jar

# Exponha a porta em que a sua aplicação Spring Boot será executada (ajuste conforme necessário)
EXPOSE 8080

# Comando para executar a sua aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "projetodelivery.jar"]