# System Update Guide (Production)

## 1. Preparation

Before updating:

* ensure system stability
* check server access
* notify users about downtime
* verify resources

---

## 2. Backup

Current version:

* no backup needed (in-memory)

Production:

* backup database
* save configuration files

Example:

pg_dump smartschool > backup.sql

---

## 3. Compatibility Check

* check dependencies
* verify Java version
* review configuration

---

## 4. Downtime Planning

* schedule update (e.g., night)
* minimize user impact

---

## 5. Update Process

### 5.1 Stop service

pkill -f smartschool

---

### 5.2 Pull new code

git pull

---

### 5.3 Build

mvn clean package

---

### 5.4 Deploy

java -jar target/*.jar

---

### 5.5 Data Migration

Not used in current version.

Production:

* run SQL migrations

---

### 5.6 Configuration Update

* check application.properties
* update environment variables

---

## 6. Post-Update Verification

Check:

* application starts correctly
* pages load
* login works
* no critical errors in logs

---

## 7. Rollback 

If something fails:

git checkout HEAD~1
mvn clean package
java -jar target/*.jar

Or:

* restore backup
* deploy previous version

---

## 8. Conclusion

Updates must be performed carefully with backup and verification.
