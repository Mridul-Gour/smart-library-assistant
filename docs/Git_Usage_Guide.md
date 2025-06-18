# Git Usage Guide for Smart Library Assistant Project

## Who Should Read This?

All developers working on this project (HTML, CSS, Backend, UI, AI etc.)

----------

## Branch Strategy

- main -> Final, clean, production-ready code.
- develop -> Latest working features; merged here after testing.
- feature/<module-name> -> Individual tasks like feature/html, feature/backend-api.

----------

## Rules

1. NEVER work directly on main.
2. Always create a new feature branch from develop:
	`git checkout develop`
	`git pull origin develop`
	`git checkout -b feature/<your-task>`
3. After completing your work:
	`git add.`
	`git commit -m "Add <description>"`
	`git push origin feature/<your-task>`
4. Inform Git Manager (Mridul) for review and merging to develop.

----------

## Common Git Commands

| Task | Command |
|------|---------|
| Clone repo (if remote later) | `git clone <repository-URL>` |
| Check current branch | `git branch` |
| Create branch | `git checkout -b feature/ <name>` |
| Switch branch | `git checkout develop` |
| Save changes | `git add . then git commit -m "msg"` |
| View changes | `git status` |
| Merge branch (by manager) | `git checkout develop -> git merge feature/xyz` |

----------

## Naming Conventions

- HTML : feature/html 
- CSS : feature/css
- JavaScript/jQuery: feature/js 
- Backend APIs: feature/backend-api
- AI Recommender: feature/ai-recommendation
- Bug Fix: bugfix/<short-name>
- Hotfix: hotfix/<short-name>

----------

## Weekly Tasks
- Deleted merged branches
- Update your local develop branch regularly
- Communicate merge conflicts with the Git Manager

----------

> Managed by Mridul Gour - Git Manager

**Repository Link**: [Smart Library Assistant Git Repo on GitHub](https://github.com/Mridul-Gour/smart-library-assistant.git)
