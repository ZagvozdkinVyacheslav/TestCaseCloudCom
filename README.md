# TestCaseCloudCom
Для работы программы необходимо оснановить postgreSQL и pg4Admin
в pg4Admin создаем базу данных и внутри этой бд создаем таблицы
В вашем pg4admin нужно создать эти 2 таблицы

CREATE TABLE users
(
    Id SERIAL PRIMARY KEY,
    username CHARACTER VARYING(30),
    password CHARACTER VARYING(30),
    enabled boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE authorities
(
    Id SERIAL PRIMARY KEY,
    username CHARACTER VARYING(30),
    authority CHARACTER VARYING(30)
);

Затем в application.properties меняем путь на свой и username и password на свои

spring.datasource.url=jdbc:postgresql://localhost/postgres
spring.datasource.username=postgres
spring.datasource.password=123456

далее можно включать программу
создаем вкладку в поисковие
http://localhost:8081/requests/main

появляется окно авторизации
тк регистрация не доделана до конца в pg4Admin users создаем пользователя(без id)
также в authoriries вводим такой же username и ROLE_USER

заходим и можно протестировать алгоритм!


