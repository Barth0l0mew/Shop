FROM  maven:3.9.4-openjdk-22.ea-b17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk
COPY --from=build /target/Shop-0.0.1-SNAPSHOT.jar Shop.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Shop.jar"]