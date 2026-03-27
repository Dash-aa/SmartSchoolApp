@echo off
echo Starting SmartSchool in DEV mode...

echo Building project...
mvn clean install

echo Running application...
mvn spring-boot:run

pause