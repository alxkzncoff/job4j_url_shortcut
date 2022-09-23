# Проект - "URL short cut"

## Общая информация

Проект разработан с целью улучшения навыков работы с различными стеками технологий и представляет
собой простой REST сервис который заменяет URL ссылки на уникальные ссылки сервиса.
При необходимости функционал проекта можно расширить.

## Запуск проекта

Для корректной работы приложения необходимо установить следующие программы:

- Java 17 или выше;
- PostgreSQL 14 или выше;
- Apache Maven 3.8.3 или выше.

1. Настройка postgreSQL. В терминале набрать следующие команды:

- Ввести логин. Вместо username указать свой;
```bash
  psql --username <username>
```
- Ввести пароль;
- Создать базу данных.
```bash
  create database chat;
```

2. Запуск при помощи maven. В терминале набрать следующие команды:
```
  mvn spring-boot:run
```

При необходимости запустить liquibase для создания таблиц в БД.
```
  mvn liquibase:update
```

## Команды

После запуска к серверу можно обратиться по адресу: http://localhost:8080

## Пользователи

- `POST /users/registration` - регистрация пользователя.
- `GET /users/userInfo` - выводит имя текущего пользователя.

## Ссылки

- `POST /links/convert` - сохраняет ссылку и возвращает уникальный код.
- `GET /links/redirect/{code}` - возвращает адрес ассоциированный с кодом.
- `GET /links/statistic` - выводит все адреса закрепленные за пользователем и их статистику вызовов.

## Технологии

[![java](https://img.shields.io/badge/java-17-red)](https://www.java.com/)
[![maven](https://img.shields.io/badge/apache--maven-3.8.3-blue)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/spring%20boot-2.7.3-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgresSQL](https://img.shields.io/badge/postgreSQL-14-blue)](https://www.postgresql.org/)

[![Actions Status](https://github.com/alxkzncoff/job4j_url_shortcut/workflows/java-ci/badge.svg)](https://github.com/alxkzncoff/job4j_url_shortcut/actions)