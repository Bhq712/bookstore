# Build vaihe
FROM maven:3.8.6-eclipse-temurin-17-focal AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Run vaihe
FROM eclipse-temurin:17-jre-focal
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/bookstore-0.0.1-SNAPSHOT.jar bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bookstore.jar"]
