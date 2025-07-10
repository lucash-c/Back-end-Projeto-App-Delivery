# Fase de Build
FROM ubuntu:latest AS build

# Atualiza os pacotes e instala o Java 17 + Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho no container
WORKDIR /app

# Copia todos os arquivos do projeto para o container
COPY . .

# Compila o projeto e gera o .jar
RUN mvn clean install -DskipTests

# Fase de execução
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

# Expõe a porta da aplicação
EXPOSE 8080

# Copia o .jar compilado da imagem de build
COPY --from=build /app/target/projetodelivery-0.0.1-SNAPSHOT.jar app.jar

# Comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

