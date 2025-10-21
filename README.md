# Buying Ticket System

Полностью переработанное приложение для продажи железнодорожных билетов на Spring Boot c подключением к PostgreSQL и простым веб-интерфейсом.

## Возможности

- CRUD-операции для пассажиров и вагонов
- Покупка билетов с автоматическим назначением мест и учетом скидок для детей и пенсионеров
- REST API, готовое к интеграции с любыми клиентами
- Построенный на Spring Boot 3, Spring Data JPA и PostgreSQL
- Простая SPA-страница (Vanilla JS) для управления пассажирами и продажей билетов

## Быстрый старт

1. Установите PostgreSQL и создайте базу данных и пользователя:

   ```sql
   CREATE DATABASE ticketing;
   CREATE USER ticket_user WITH PASSWORD 'change_me';
   GRANT ALL PRIVILEGES ON DATABASE ticketing TO ticket_user;
   ```

2. Обновите файл [`src/main/resources/application.properties`](src/main/resources/application.properties), если вы используете другие параметры подключения.

3. Запустите приложение:

   ```bash
   mvn spring-boot:run
   ```

4. Откройте [http://localhost:8080](http://localhost:8080) чтобы воспользоваться веб-интерфейсом.

## Тесты

Во время тестирования используется встроенная база H2 в режиме совместимости с PostgreSQL. Запуск тестов:

```bash
mvn test
```

## Структура проекта

- `com.example.ticketsystem.model` — JPA-сущности (пассажиры, вагоны)
- `com.example.ticketsystem.service` — бизнес-логика и работа с репозиториями
- `com.example.ticketsystem.controller` — REST-контроллеры для API
- `src/main/resources/static` — статический фронтенд

