FROM jelastic/maven:3.9.4-openjdk-22.ea-b17
COPY ./src src/
COPY ./pom.xml pom.xml
RUN mvn clean packege -DskipTests
FROM openjdk:22-jdk
COPY --from=build  target/Shop-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]