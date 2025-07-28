########################################
## Build Stage                        ##
########################################
FROM maven:3.9.6-eclipse-temurin-22 AS build
WORKDIR /build

# Step 1: Copy required POM files
COPY pom.xml .
COPY api-gateway/pom.xml api-gateway/pom.xml
COPY common/pom.xml common/pom.xml

# Step 2: Preload dependencies
RUN --mount=type=cache,id=m2,target=/root/.m2 \
    mvn -f api-gateway/pom.xml dependency:go-offline -B

# Step 3: Copy only relevant source code
COPY api-gateway/src/ api-gateway/src/
COPY common/src/ common/src/

# Step 4: Build the JAR
RUN --mount=type=cache,id=m2,target=/root/.m2 \
    mvn -f api-gateway/pom.xml clean package -B -DskipTests

########################################
## Runtime Stage                      ##
########################################
FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app

# Copy only the built JAR
COPY --from=build /build/api-gateway/target/api-gateway-*.jar ./api-gateway.jar

# Set port
EXPOSE 8180

# Run the app
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]