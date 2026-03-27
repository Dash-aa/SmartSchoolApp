# Backup Guide

## 1. Current State

* No database
* Data is not persistent

## 2. Backup Example

pg_dump smartschool > backup.sql

## 3. Restore

psql smartschool < backup.sql

## 4. Frequency

* daily
* before updates

## 5. Note

Backup required only with real DB.
