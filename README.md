# Гринтех

## Как запустить?

#### 1) Установите Docker и Docker compose.

#### 2) Скопируйте себе файл [docker-compose.yml](./greentech-backend/docker-compose.yml) и перейдите в директорию с ним.

#### 3) В командной строке введите:
```
docker-compose up -d
```
Приложение начнет работать по адресу http://localhost:8080

## Документация

По адресу http://localhost:8080/api/swagger-ui/index.html доступна документация OpenApiSpecification.

## Аутентификация

### В приложении используется Bearer Token. Срок действия токена 1 час. Алгоритм работы следующий:

#### 1) Получить токен, используя /api/register или /api/login.

#### 2) При каждом запросе требуется передавать полученный токен в заголовках запроса. Заголовок - "Authorization", значение - "Bearer your-token".
