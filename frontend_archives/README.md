This folder is used to store daily backups and file change snapshots of the `frontend/` directory. It is automatically maintained by the `auto_archive_and_log.sh` script.

## Contents:
- **`frontend_backup_YYYY-MM-DD.tar.gz`**  
  Compressed archive of the entire `frontend/` folder for that specific date.

- **`snapshot_YYYY-MM-DD.txt`**  
  List of file checksums (MD5) for all frontend files on the given day, used to detect file changes.

- **`latest_snapshot.txt`**  
  Most recent snapshot used to compare changes between two runs.

## 🛠 How It Works:
- The script runs daily (via manually).
- It checks for any changes in frontend files.
- It archives the frontend folder.
- It logs detected changes into `logs/change_log.txt`.

##  Notes:
- This folder helps with **debugging**, **recovery**, and **tracking updates** during development.
- Do **not** modify or delete files inside this folder unless necessary.
