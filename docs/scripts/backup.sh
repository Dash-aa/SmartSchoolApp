#!/bin/bash

echo "Starting backup..."

mkdir -p backup

if [ -f application.properties ]; then
cp application.properties backup/application.properties.bak
fi

if [ -f app.log ]; then
cp app.log backup/app.log.bak
fi

echo "Backup done."
