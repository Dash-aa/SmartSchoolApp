# Deployment Guide

## 1. Overview

This document describes deployment of SmartSchool.

## 2. Requirements

* CPU: 2 cores
* RAM: 2 GB
* Disk: 5 GB

## 3. Software

* Java 21
* Maven
* Git

## 4. Network

Open port 8080

## 5. Clone

git clone https://github.com/Dash-aa/SmartSchoolApp.git
cd SmartSchoolApp

## 6. Build

mvn clean package

## 7. Run

java -jar target/*.jar

## 8. Database

No DB (in-memory).

## 9. Check

http://localhost:8080
