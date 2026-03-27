# System Update Guide

## 1. Preparation

* backup
* check compatibility

## 2. Stop

pkill -f smartschool

## 3. Update

git pull

## 4. Build

mvn clean package

## 5. Run

java -jar target/*.jar

## 6. Check

* app works
* login works

## 7. Rollback

git checkout HEAD~1
mvn clean package
java -jar target/*.jar
