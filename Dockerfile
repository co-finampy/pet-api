# Etapa de build: Usa uma imagem com Maven e OpenJDK 17 já instalados
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Executa o Maven diretamente, sem `mvnw`
RUN mvn clean package -DskipTests

# Etapa de runtime: Usa uma imagem menor do JDK para rodar a aplicação
FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho no container
WORKDIR /app

# Expõe a porta da aplicação
EXPOSE 8080

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
