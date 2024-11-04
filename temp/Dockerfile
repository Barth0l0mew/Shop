# Переключаемся на меньший образ для запуска
FROM openjdk:22-jdk-slim

# Установите рабочую директорию для приложения
WORKDIR /Shop

# Скопируйте jar файл из образа сборки
COPY  /Shop/target/Shop-0.0.1-SNAPSHOT.jar app.jar
# Укажите команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.datasource.url=jdbc:h2:file:/app/data/mydb;DB_CLOSE_ON_EXIT=FALSE:DB_CLOSE_DELAY=-1"]

# Укажите порт, который ваше приложение будет использовать
EXPOSE 8080