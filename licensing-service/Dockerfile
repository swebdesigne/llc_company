# Шаг 1
# Базовый образ, содержащий среду Java времени выполнения
FROM openjdk:17-slim as build
# Добавить информацию о владельце
LABEL maintainer="Igor Sivolobov <swebdesign@gmail.com>"

# Файл jar приложения
ARG JAR_FILE
# Добавить файл jar приложения в контейнер
COPY licensing-service/build/libs/licensing-service.jar app.jar
#COPY build/libs/licensing-service.jar app.jar
# распаковать файл jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# Шаг 2
# Та же среда Java времени выполнения
FROM openjdk:17-slim
# Добавить том, ссылающийся на каталог /tmp
VOLUME /tmp

# Распаковывает app.jar, скопированный ранее, в файловую систему образа
# Этот новый образ содержит несколько слоев приложения Spring Boot полного файла JAR
# Копирует отдельные слои из первого образа с именем build
# Скопировать распакованное приложение в новый контейнер
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Определяет службу для запуска после создания контейнера
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.llcgrowth.license.LicensingServiceApplication"]
