# SmartSchool Backup Guide

## 1. Strategy

Current version uses in-memory storage.

For production:

* database backup
* config backup
* logs backup

---

## 2. Types

* Full
* Incremental
* Differential

---

## 3. Frequency

* weekly full
* daily backup
* before updates

---

## 4. Storage

* local
* server
* cloud

---

## 5. Procedure

Database:
pg_dump smartschool > backup.sql

Configs:
cp application.properties backup/application.properties.bak

Logs:
cp app.log backup/app.log.bak

---

## 6. Verification

* file exists
* file not empty

---

## 7. Automation

Scripts:

* backup.sh
* backup.bat

---

## 8. Restore

psql smartschool < backup.sql

---

## 9. Conclusion

Backup required only for production.
