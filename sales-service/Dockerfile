# ---------- 1) Build Stage ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy root pom and all module pom.xml files for dependency resolution
COPY pom.xml .
COPY sales-service/pom.xml sales-service/pom.xml
COPY client-service/pom.xml client-service/pom.xml
COPY product-service/pom.xml product-service/pom.xml
COPY api-gateway/pom.xml api-gateway/pom.xml
COPY common/pom.xml common/pom.xml

# Preload Maven dependencies
RUN mvn -B dependency:go-offline

# Copy full source code for required modules
COPY sales-service/ sales-service/
COPY client-service/ client-service/
COPY product-service/ product-service/
COPY api-gateway/ api-gateway/
COPY common/ common/

# Build only sales-service and its dependencies
RUN mvn -B -pl sales-service -am -DskipTests package

# ---------- 2) Runtime Stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /workspace/sales-service/target/*.jar app.jar

# Expose the application port
EXPOSE 8183

# Run the service
ENTRYPOINT ["java", "-jar", "app.jar"]
