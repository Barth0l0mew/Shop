#Рабочий докер
# Используйте официальный образ OpenJDK 21 как базовый
FROM openjdk:22-jdk-slim as build

# Установите рабочую директорию
WORKDIR /Shop

# Скопируйте файл сборки (например, pom.xml или build.gradle) в текущую директорию
COPY pom.xml .

COPY mvnw ./
COPY .mvn ./.mvn
RUN chmod +x mvnw
# Скачиваем зависимости (первый шаг сборки)
RUN ./mvnw dependency:go-offline

# Копируем остальные файлы проекта
COPY src ./src

# Собрать приложение
RUN ./mvnw package -DskipTests

# Переключаемся на меньший образ для запуска
FROM openjdk:22-jdk-slim
# Укажите путь, где H2 база данных будет храниться внутри контейнера
VOLUME /Shop/data
# Установите рабочую директорию для приложения
WORKDIR /Shop

# Скопируйте jar файл из образа сборки
COPY --from=build /Shop/target/*.jar app.jar

# Укажите команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]

# Укажите порт, который ваше приложение будет использовать
EXPOSE 8080