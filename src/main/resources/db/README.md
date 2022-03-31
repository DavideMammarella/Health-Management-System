# Production Database Customization
These instructions allow you to work on the production database independently. <br>
**Pay attention** to every change you make because it must also be reflected in the application. <br>
In fact, the application automatically validates the database by verifying its tables with those expressed in the data access layer. If the validation does not take place then the application will fail to start.

## Instructions

- Create a Docker image with a PostgreSQL database inside:
```
docker create --name postgres-insalute -e POSTGRES_PASSWORD=abracadabra -e POSTGRES_DB=insalute_db -p 5432:5432 postgres:11.5-alpine
```

- Create database tables via SQL file (the file must be in the root of your system)
```
docker cp create_tables.sql postgres-insalute:/create_tables.sql
docker exec -it postgres-insalute psql -d insalute_db -f create_tables.sql -U postgres
```

- Insert data via SQL file (the file must be in the root of your system)
```
docker cp insert_data.sql postgres-insalute:/insert_data.sql
docker exec -it postgres-insalute psql -d insalute_db -f insert_data.sql -U postgres
```

Now you can use all the **Docker** and **PSQL** commands to make additional changes on the database.

## Database Information

- **Docker container name**: postgres-insalute
- **PostgreSQL database name**: insalute_db
- **PostgreSQL username**: postgres
- **PostgreSQL password**: abracadabra
- **PostgreSQL ports**: 5432:5432