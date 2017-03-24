@echo off
echo.

REM set location of sql files 
set sql_dir=%~dp0\sql

REM check if postgres executables are on the path, prompt for directory if not
where /q psql
IF ERRORLEVEL 1 (
    set /p pg_dir="Enter postgres root directory (e.g. "C:\Program Files\PostgreSQL\9.6"): "
	set pg_bin=%pg_dir%"\bin
) ELSE (
	for /f "delims=" %%F in ('where psql') do set var=%%F
    set pg_bin="
)

REM Prompt for postgres admin username and password
set /p PGUSER="Enter postgres Admin Username: "
powershell -Command $pword = read-host "Enter password" -AsSecureString ; $BSTR=[System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($pword) ; [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR) > .tmp.txt & set /p password=<.tmp.txt & del .tmp.txt
set PGPASSWORD=%password%
echo.

REM run sql commands to drop database and user if existing, create database, user, and tables, and fill tables
%pg_bin%\psql" -d evaluations -a -f %sql_dir%\pg_kill_connections.sql
%pg_bin%\dropdb" --if-exists evaluations
%pg_bin%\psql" -d postgres -a -f %sql_dir%\pg_create_user.sql
%pg_bin%\createdb" -O evaluations evaluations
%pg_bin%\psql" -d evaluations -a -f %sql_dir%\pg_create_tables.sql
%pg_bin%\psql" -d evaluations -a -f %sql_dir%\pg_fill_tables.sql
echo.
echo.


REM Display connection info to user
echo ----- Connection Information: -----
echo.
echo USERNAME: evaluations
echo PASSWORD: p4ssw0rd
echo JDBC URL: jdbc:postgresql://localhost:5432/evaluations
echo.

REM Set environment variables if desired
set /p set_env="Set environment variables? (y/n): "
if /I "%set_env%"=="y" (
	setx INTEVAL_USER evaluations > nul
	setx INTEVAL_PASS p4ssw0rd > nul
	setx INTEVAL_URL jdbc:postgresql://localhost:5432/evaluations > nul
	echo.
	echo Environment Variables set. Restart your IDE to detect changes.
)

echo.
pause
