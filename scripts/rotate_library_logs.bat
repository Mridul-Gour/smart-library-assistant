@echo off
setlocal enabledelayedexpansion

:: === Smart Library Assistant - Log Rotation Script (Fixed Relative Path) ===

:: Calculate log folder path relative to script
pushd %~dp0
cd ..
set LOG_DIR=%CD%\logs
popd

set DATE_SUFFIX=%DATE:~10,4%-%DATE:~4,2%-%DATE:~7,2%

:: Check if logs folder exists
if not exist "%LOG_DIR%" (
    echo ERROR: Log folder "%LOG_DIR%" does not exist. Please create it and add logs.
    pause
    exit /b
)

echo Rotating and compressing logs in %LOG_DIR%...

:: Loop through all .log and .txt files
for %%f in ("%LOG_DIR%\*.log" "%LOG_DIR%\*.txt") do (
    echo Processing file: %%f

    set "NEW_FILE=%LOG_DIR%\%%~nf-%DATE_SUFFIX%%%~xf"

    echo Renaming to !NEW_FILE!
    ren "%%f" "%%~nf-%DATE_SUFFIX%%%~xf"

    echo Compressing !NEW_FILE!
    powershell -Command "Compress-Archive -Path '!NEW_FILE!' -DestinationPath '!NEW_FILE!.zip'"

    echo Deleting original file after compression
    del "!NEW_FILE!"
)

:: Delete zip files older than 30 days
echo Deleting .zip files older than 30 days...
forfiles /p "%LOG_DIR%" /s /m *.zip /d -30 /c "cmd /c del @path"

echo Log rotation completed!
pause
endlocal
