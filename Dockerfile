# Estágio de construção
FROM maven:3.8-openjdk-17 AS build

COPY . /app
WORKDIR /app

RUN mvn clean install

# Estágio final
FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/projetodelivery-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]







# Comando para executar a sua aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "projetodelivery.jar"]