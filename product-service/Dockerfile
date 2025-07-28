# syntax=docker/dockerfile:1.3
FROM maven:3.9.6-eclipse-temurin-22 AS build
WORKDIR /build

# Step 1: Copy all required POMs first
COPY pom.xml .
COPY common/pom.xml common/pom.xml
COPY product-service/pom.xml product-service/pom.xml

# Step 2: Install the root aggregator POM to local .m2
RUN --mount=type=cache,id=m2,target=/root/.m2 \
    mvn install -N -B

# Step 3: Install common module
COPY common/src/ common/src/
RUN --mount=type=cache,id=m2,target=/root/.m2 \
    mvn -f common/pom.xml clean install -B -DskipTests

# Step 4: Preload product-service dependencies
RUN --mount=type=cache,id=m2,target=/root/.m2 \
    mvn -f product-service/pom.xml dependency:go-offline -B

# Step 5: Copy product-service code and build
COPY product-service/src/ product-service/src/
RUN --mount=type=cache,id=m2,target=/root/.m2 \
    mvn -f product-service/pom.xml clean package -B -DskipTests

########################################
## Runtime Stage                      ##
########################################
FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app

# Copy only the built JAR
COPY --from=build /build/product-service/target/product-service-*.jar product-service.jar

# Expose the port
EXPOSE 8181

# Run the application
ENTRYPOINT ["java", "-jar", "product-service.jar"]
