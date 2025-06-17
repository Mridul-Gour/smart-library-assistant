#!/bin/bash

# Define paths
FRONTEND_DIR="$HOME/smart-library-assistant/frontend"
ARCHIVE_DIR="$HOME/smart-library-assistant/frontend_archives"
LOG_DIR="$HOME/smart-library-assistant/logs"
LOG_FILE="$LOG_DIR/change_log.txt"

# Create folders if they don't exist
mkdir -p "$ARCHIVE_DIR"
mkdir -p "$LOG_DIR"

# Get today's date
TODAY=$(date +%Y-%m-%d)
ARCHIVE_NAME="frontend_backup_$TODAY.tar.gz"

# Archive frontend files
tar -czf "$ARCHIVE_DIR/$ARCHIVE_NAME" -C "$FRONTEND_DIR" .

# Logging changes using file hashes
PREV_LOG="$ARCHIVE_DIR/latest_snapshot.txt"
CURRENT_LOG="$ARCHIVE_DIR/snapshot_$TODAY.txt"

find "$FRONTEND_DIR" -type f -exec md5sum {} \; > "$CURRENT_LOG"

echo "[$(date '+%Y-%m-%d %H:%M:%S')] Checking for changes..." >> "$LOG_FILE"

if [ -f "$PREV_LOG" ]; then
    echo "=== File Changes on $TODAY ===" >> "$LOG_FILE"
    diff "$PREV_LOG" "$CURRENT_LOG" >> "$LOG_FILE"
    echo "" >> "$LOG_FILE"
else
    echo "First run, no previous snapshot found. No diff available." >> "$LOG_FILE"
fi

# Update the latest snapshot for next comparison
cp "$CURRENT_LOG" "$PREV_LOG"
