FROM openjdk:22-jdk
COPY --from=build /target/Shop-0.0.1-SNAPSHOT.jar Shop.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Shop.jar"]