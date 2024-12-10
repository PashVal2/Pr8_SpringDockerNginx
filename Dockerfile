# Этап 1: Сборка проекта
FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

# Устанавливаем необходимые библиотеки для работы с шрифтами и графикой
RUN apt-get update && apt-get install -y \
    libfreetype6 \
    libfontconfig1 \
    libxrender1 \
    libx11-6

# Копируем только необходимые файлы для сборки
COPY build.gradle.kts settings.gradle.kts /app/
COPY gradlew /app/
COPY gradle /app/gradle

# Загружаем зависимости и собираем проект
RUN ./gradlew build -x test

# Этап 2: Финальный контейнер
FROM openjdk:17-jdk-slim

WORKDIR /app

# Устанавливаем библиотеки для работы с шрифтами и графикой
RUN apt-get update && apt-get install -y \
    libfreetype6 \
    libfontconfig1 \
    libxrender1 \
    libx11-6

# Копируем только необходимые артефакты из предыдущего этапа
COPY --from=builder /app/build/libs /app/build/libs

# Копируем весь проект в финальный контейнер
COPY . /app

# Устанавливаем JVM аргументы для безголового режима (если нужно)
ENV JAVA_OPTS="-Djava.awt.headless=true"

# Открываем порт для приложения
EXPOSE 8080

# Запуск приложения
CMD ["java", "-jar", "build/libs/Pr8CodeRefactor-1.0.0-SNAPSHOT.jar"]
