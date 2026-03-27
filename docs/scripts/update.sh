#!/bin/bash

echo "Starting update..."

echo "Pulling latest code..."
git pull

echo "Building project..."
mvn clean package

echo "Restart application manually if needed."