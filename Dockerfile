# Etapa de build (Usando uma imagem que já vem com Maven e JDK instalados)
FROM maven:3.8.6-openjdk-21 AS build

# Define o diretório de trabalho no container
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Executa o Maven para compilar o projeto e gerar o JAR
RUN mvn clean package -DskipTests

# Etapa de runtime (Executa a aplicação com uma imagem mais leve)
FROM openjdk:21-jdk

# Define o diretório de trabalho no container
WORKDIR /app

# Expõe a porta da aplicação
EXPOSE 8080

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
