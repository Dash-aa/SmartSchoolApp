# Оновлення системи

## 1. Підготовка

* backup
* перевірка сумісності

## 2. Зупинка

pkill -f smartschool

## 3. Оновлення

git pull

## 4. Збірка

mvn clean package

## 5. Запуск

java -jar target/*.jar

## 6. Перевірка

* сайт працює
* логін працює

## 7. Rollback

git checkout HEAD~1
mvn clean package
java -jar target/*.jar
