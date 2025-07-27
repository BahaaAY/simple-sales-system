# ---------- 1) Build Stage ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy root pom.xml and module pom.xmls for caching dependencies
COPY pom.xml .
COPY api-gateway/pom.xml api-gateway/pom.xml
COPY client-service/pom.xml client-service/pom.xml
COPY product-service/pom.xml product-service/pom.xml
COPY sales-service/pom.xml sales-service/pom.xml
COPY common/pom.xml common/pom.xml

# Preload dependencies
RUN mvn -B dependency:go-offline

# Copy full module source code
COPY api-gateway/ api-gateway/
COPY client-service/ client-service/
COPY product-service/ product-service/
COPY sales-service/ sales-service/
COPY common/ common/

# Build the api-gateway module and its dependencies
RUN mvn -B -pl api-gateway -am -DskipTests package

# ---------- 2) Runtime Stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /workspace/api-gateway/target/*.jar app.jar

# Expose the application port
EXPOSE 8180

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
