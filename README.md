# Mirs Film

Hệ thống đặt vé xem phim, xây để học: Spring Boot 4.1, React 19.2, PostgreSQL 18.
Đặc tả và lộ trình 9 phase: https://claude.ai/code/artifact/e7a78e68-37a1-43fa-b74d-1a440ade65d7

## Cấu trúc

```
backend/            Spring Boot (Maven wrapper, Java 21+)
frontend/apps/web   trang đặt vé cho khách (Vite + React + TS)
frontend/apps/admin trang quản trị (Vite + React + TS)
docs/adr/           quyết định kiến trúc
docs/learning/      bài tập và ghi chú từng phase
docker-compose.yml  hạ tầng local (Phase 0: chỉ Postgres)
```

## Cần cài

- JDK 21 trở lên, Docker Desktop, Node 22, pnpm (`corepack enable pnpm`, có thể cần quyền admin).

## Chạy local (mỗi tiến trình một terminal, chạy từ thư mục gốc)

```bash
docker compose up -d
cd backend && ./mvnw spring-boot:run      # http://localhost:8080/actuator/health
cd frontend && pnpm install
pnpm dev:web                              # http://localhost:5173
pnpm dev:admin                            # http://localhost:5174
```

## Test

```bash
cd backend && ./mvnw verify               # cần Docker đang chạy (Testcontainers)
cd frontend && pnpm lint && pnpm build
```
