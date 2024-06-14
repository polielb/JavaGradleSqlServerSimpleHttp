# IMPORTANTE ....
tener descargado la dll mssql-jdbc_auth-9.2.1.x64.dll desde 
https://learn.microsoft.com/en-us/sql/connect/jdbc/release-notes-for-the-jdbc-driver?view=sql-server-ver16
https://go.microsoft.com/fwlink/?linkid=2155948

copiar la dll en 
C:\Windows\System32 
y en 
C:\Program Files\Java\jdk-22\bin


# My Gradle SQL Server Project

This project demonstrates how to connect to a SQL Server database using Java and Gradle, and execute a simple query.

## Configuration

Update the `src/main/resources/application.properties` file with your database connection details.

## probar

abrir con  IntelliJ IDEA 2024.1.2 (Community Edition) pararce en App.java y dale Play
ir a 
http://localhost:8081/DameFechaHora

## Build and Run

```sh
gradlew.bat clean

gradlew.bat build

java -jar build/libs/javaGradleSqlServerSimple.jar
Servidor HTTP iniciado en el puerto 8081

http://localhost:8081/DameFechaHora



