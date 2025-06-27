@echo off
setlocal enabledelayedexpansion

:: === Log Rotation Script for application.log (Smart Library Assistant) ===

:: Set log folder path relative to this script location
pushd %~dp0
cd ..
set "LOG_DIR=%CD%\logs"
popd

:: Check if application.log exists
if not exist "%LOG_DIR%\application.log" (
    echo [ERROR] File "%LOG_DIR%\application.log" not found.
    pause
    exit /b
)

:: Generate date suffix (YYYY-MM-DD)
set DATE_SUFFIX=%DATE:~10,4%-%DATE:~4,2%-%DATE:~7,2%

echo [INFO] Rotating and compressing application.log from: %LOG_DIR%

:: Rename application.log to application-YYYY-MM-DD.log
set "RENAMED_LOG=%LOG_DIR%\application-%DATE_SUFFIX%.log"
echo [INFO] Renaming application.log to application-%DATE_SUFFIX%.log
ren "%LOG_DIR%\application.log" "application-%DATE_SUFFIX%.log"

:: Compress the renamed log
echo [INFO] Compressing application-%DATE_SUFFIX%.log into ZIP
powershell -Command "Compress-Archive -Path '%RENAMED_LOG%' -DestinationPath '%RENAMED_LOG%.zip'"

:: Delete the original .log after compression
echo [INFO] Deleting the original .log file after compression
del "%RENAMED_LOG%"

:: Delete ZIP files older than 30 days
echo [INFO] Cleaning up .zip logs older than 30 days
forfiles /p "%LOG_DIR%" /s /m *.zip /d -30 /c "cmd /c del @path"

echo [INFO] Log rotation completed for application.log!
pause
endlocal

