# Проект - "URL short cut"

## Общая информация

Проект разработан с целью улучшения навыков работы с различными стеками технологий и представляет
собой простой REST сервис который заменяет URL ссылки на уникальные ссылки сервиса.
При необходимости функционал проекта можно расширить.

## Запуск проекта

Для корректной работы приложения необходимо установить следующие программы:

- [Docker](https://docs.docker.com/engine/install/)
- [Docker-compose](https://docs.docker.com/compose/install/)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [minikube](https://minikube.sigs.k8s.io/docs/start/)

1. Запуск проекта с помощью Docker:

Скопировать себе проект:
```
git clone https://github.com/alxkzncoff/job4j_url_shortcut.git
```

Перейти в директорию проекта:
```
cd <path>/job4j_url_shortcut
```

где ```path``` путь до проекта.

Собрать контейнер и запустить:
```
docker-compose up --build
```

2. Развертывание и запуск кластера Kubernetes:

Перейти в папку с конфигурациоными файлами:

```
cd <path>/job4j_url_shortcut/k8s
```

где ```path``` путь до проекта.

Развертывание:

```
kubectl apply -f postgresdb-secret.yml
kubectl apply -f postgresdb-configmap.yml
kubectl apply -f postgresdb-deployment.yml
kubectl apply -f spring-deployment.yml
```

Запуск:

```
minikube service spring-boot-service
```

## Команды

После запуска к серверу можно обратиться по адресу: http://localhost:8080

### Пользователи

- `POST /users/registration` - регистрация пользователя.
- `GET /users/userInfo` - выводит имя текущего пользователя.

### Ссылки

- `POST /links/convert` - сохраняет ссылку и возвращает уникальный код.
- `GET /links/redirect/{code}` - возвращает адрес ассоциированный с кодом.
- `GET /links/statistic` - выводит все адреса закрепленные за пользователем и их статистику вызовов.

### Примеры curl запросов

- Регистрация пользователя:

```
curl --location --request POST 'http://localhost:8080/users/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "site" : "https://example.com/"
}'
```

Ответ будет получен в виде JSON:

```
{
    "registration": true,
    "login": "example_com",
    "password": "L[MYec)*Y0"
}
```

- Авторизация пользователя:

```
curl --location --request POST 'http://localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "example_com",
    "password": "L[MYec)*Y0"
}'
```

- Конвертация ссылки. Требуется авторизация.

```
curl --location --request POST 'http://localhost:8080/links/convert' \
--header 'Authorization: Bearer <your bearer toke>' \
--header 'Content-Type: application/json' \
--data-raw '{
    "url": "https://example.com/page/1"
}'
```

где ```<your bearer toke>``` токен авторизации.


## Технологии

[![java](https://img.shields.io/badge/java-17-red)](https://www.java.com/)
[![maven](https://img.shields.io/badge/apache--maven-3.8.3-blue)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/spring%20boot-2.7.3-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgresSQL](https://img.shields.io/badge/postgreSQL-14-blue)](https://www.postgresql.org/)

[![Actions Status](https://github.com/alxkzncoff/job4j_url_shortcut/workflows/java-ci/badge.svg)](https://github.com/alxkzncoff/job4j_url_shortcut/actions)
