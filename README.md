# TestCaseCloudCom
Для работы программы необходимо оснановить postgreSQL и pg4Admin
в pg4Admin создаем базу данных и внутри этой бд создаем таблицы
Затем в application.properties меняем путь на свой и username и password на свои

spring.datasource.url=jdbc:postgresql://localhost/postgres
spring.datasource.username=postgres
spring.datasource.password=123456

далее можно включать программу
создаем вкладку в поисковие
http://localhost:8081/requests/main

Работа алгоритма:
На вход алгоритм получает 2 строки на русском языке, затем производится понижение регистра, удаление стоп слов, знаков пунктуации и цифр,
далее ипользуя программу MyStemp производится лемматизация слов в строке. Далее происходит сравнение оставшихся строк на одинаковые слова
и выдается процентное содержание слов 2 строки в первой.


