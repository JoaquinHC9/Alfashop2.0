@echo off
SET BACKUP_FILE=%1
SET MYSQL_CONTAINER=mysqldb
SET MYSQL_USER=root
SET MYSQL_PASSWORD=root
SET DB_NAME=alfashop

:: Verificar si el contenedor está listo
echo Esperando a que la base de datos esté lista...
FOR /L %%i IN (1,1,30) DO (
    docker exec %MYSQL_CONTAINER% mysqladmin ping -u %MYSQL_USER% -p%MYSQL_PASSWORD% --silent
    IF %ERRORLEVEL% == 0 (
        echo La base de datos está lista.
        GOTO CONTINUE
    )
    echo Esperando...
    timeout /t 5 >nul
)
echo Error: Tiempo de espera agotado para que la base de datos esté lista.
exit /b 1

:CONTINUE
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
