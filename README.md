# Bookstore

Simple Bookstore application used to practice full-stack development.

## About

This repository contains a multi-service Bookstore project with separate folders for the backend, frontend, nginx configuration, and book image assets.

## Project structure

```
bookstore/
├─ backend/        # API server (Java)
├─ frontend/       # Static frontend (HTML/CSS/JS)
├─ nginx/          # nginx config to serve frontend and proxy to backend
├─ book-images/    # static image assets used by the app
├─ docker-compose.yaml
```

## Prerequisites

* Docker
* Docker Compose (v2 `docker compose` recommended)

## Quickstart

From the repository root:

1. Build images only (no containers started):

```bash
docker compose build
```

2. Build images and start all services:

```bash
docker compose up --build
```

3. Stop and remove containers (leave images):

```bash
docker compose down
```

## Troubleshooting

* If a service fails at build time, run `docker compose build --no-cache <service>` to force a clean rebuild.
* Use `docker compose logs -f <service>` to tail logs while debugging.
