FROM eclipse-temurin:21-jre-alpine

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR файл из локальной машины в контейнер
COPY target/Task-Management-0.0.1-SNAPSHOT.jar /app/Task-Management-0.0.1-SNAPSHOT.jar

# Указываем, что контейнер будет слушать на порту 8080
EXPOSE 8080

# Определяем команду запуска приложения
ENTRYPOINT ["java", "-jar", "/app/Task-Management-0.0.1-SNAPSHOT.jar"]