@echo off
SET BACKUP_FILE=%1
SET MYSQL_CONTAINER=mysqldb
SET MYSQL_USER=root
SET MYSQL_PASSWORD=root
SET DB_NAME=alfashop

:: Copiar el archivo de backup al contenedor
docker cp %BACKUP_FILE% %MYSQL_CONTAINER%:/tmp/backup.sql
IF NOT %ERRORLEVEL% == 0 (
    echo Error al copiar el archivo al contenedor.
    exit /b 1
)

:: Restaurar la base de datos
docker exec %MYSQL_CONTAINER% bash -c "mysql -u %MYSQL_USER% -p%MYSQL_PASSWORD% %DB_NAME% < /tmp/backup.sql"
IF NOT %ERRORLEVEL% == 0 (
    echo Error al restaurar la base de datos.
    exit /b 1
)

echo Restauración de la base de datos completada con éxito.
exit /b 0
