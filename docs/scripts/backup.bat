@echo off
echo Starting backup...

if not exist backup mkdir backup

if exist application.properties copy application.properties backup\application.properties.bak > nul
if exist app.log copy app.log backup\app.log.bak > nul

echo Backup done.
pause
