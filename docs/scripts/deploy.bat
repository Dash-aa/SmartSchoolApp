@echo off
echo Starting deployment...

echo Building project...
mvn clean package

echo Running application...
java -jar target\*.jar

pause