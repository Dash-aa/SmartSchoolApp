@echo off
echo Starting SmartSchool in PRODUCTION mode...

echo Building project...
mvn clean package

echo Running application...
java -jar target\*.jar

pause