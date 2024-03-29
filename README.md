# PA Shop

## Requirements

- Node 8+.
- Java SE 8+.
- Maven 3+.
- MySQL 8+.

## Database creation

```
Start Mysql server if not running (e.g. mysqld).

mysqladmin -u root create tfg
mysqladmin -u root create tfgtest

mysql -u root
    CREATE USER 'tfg'@'localhost' IDENTIFIED BY 'tfg';
    GRANT ALL PRIVILEGES ON tfg.* to 'tfg'@'localhost' WITH GRANT OPTION;
    GRANT ALL PRIVILEGES ON tfgtest.* to 'tfg'@'localhost' WITH GRANT OPTION;
    exit
```

## Run

```
cd backend
mvn sql:execute (only first time to create tables)
mvn spring-boot:run

cd frontend
npm install (only first time to download libraries)
npm start
```
