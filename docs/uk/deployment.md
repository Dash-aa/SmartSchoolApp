# Розгортання застосунку (Production)

## 1. Опис

Цей документ описує процес розгортання веб-застосунку SmartSchool у production-середовищі.

## 2. Вимоги до обладнання

* CPU: 2 ядра
* RAM: 2 ГБ
* Диск: 5 ГБ
* ОС: Linux (Ubuntu 20.04+)

## 3. Необхідне ПЗ

* Java JDK 21
* Maven
* Git

## 4. Налаштування мережі

* Відкрити порт 8080
* Дозволити HTTP-з'єднання

## 5. Клонування

git clone https://github.com/Dash-aa/SmartSchoolApp.git
cd SmartSchoolApp

## 6. Збірка

mvn clean package

## 7. Запуск

java -jar target/*.jar

## 8. База даних

Не використовується (in-memory).

## 9. Перевірка

http://localhost:8080

## 10. Логи

java -jar target/*.jar > app.log
