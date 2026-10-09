# Phase 0 — Nền móng

Khung dự án đã có sẵn (backend Spring Boot 4.1, hai app React 19.2, Postgres 18 qua Docker Compose, CI).
Các bài dưới đây là phần **bạn tự làm**: chúng là lý do Phase 0 tồn tại.
Mỗi bài có dòng **Bằng chứng**: đó là thứ Claude sẽ kiểm tra ở cổng phase.

## Bài tập

- [ ] **1. Chạy được toàn bộ.** Bật Docker Desktop → `docker compose up -d` → `cd backend && ./mvnw spring-boot:run`
      → mở http://localhost:8080/actuator/health, phải thấy `"status":"UP"`.
      Bằng chứng: Claude tự chạy lại khi kiểm tra.
- [ ] **2. Migration đầu tiên.** Tạo `backend/src/main/resources/db/migration/V1__enable_extensions.sql` với
      `CREATE EXTENSION IF NOT EXISTS citext;` (Phase 1 dùng `citext` cho email không phân biệt hoa thường).
      Chạy lại app, rồi xem bảng `flyway_schema_history` bằng `docker compose exec postgres psql -U mirsfilm`.
      Bằng chứng: file V1 đã commit; `flyway_schema_history` có dòng version 1.
- [ ] **3. Phá migration.** Sửa một ký tự trong V1 đã chạy rồi khởi động lại. Đọc lỗi, giải thích vì sao Flyway từ chối.
      Hoàn tác, và ghi lại: muốn đổi schema thì làm gì thay vì sửa V1?
      Bằng chứng: mục Ghi chú có tên lỗi và cách đúng để đổi schema.
- [ ] **4. Đọc auto-configuration.** Chạy app với `--debug`, tìm `DataSourceAutoConfiguration` và `FlywayAutoConfiguration`
      trong Conditions Evaluation Report. Vì sao chúng được bật? Bỏ dependency `postgresql` khỏi `pom.xml` thì app lỗi gì?
      Bằng chứng: mục Ghi chú có điều kiện (`@Conditional...`) đã khớp và thông báo lỗi khi bỏ driver.
- [ ] **5. Profiles.** Tạo `application-local.properties` bật log SQL (`logging.level.org.hibernate.SQL=debug`),
      chạy với `--spring.profiles.active=local`. Thử đặt cùng một property ở file, biến môi trường và tham số dòng lệnh:
      cái nào thắng?
      Bằng chứng: file `application-local.properties` đã commit; mục Ghi chú có thứ tự ưu tiên bạn quan sát được.
- [ ] **6. Ranh giới module.** Tạo hai package `com.mirsfilm.catalog` và `com.mirsfilm.booking`. Cho một class trong `booking`
      dùng một class trong `catalog.internal`, chạy `./mvnw test -Dtest=ModularityTests` và đọc lỗi. Sửa lại cho đúng ranh giới.
      Xong thì xóa code thử.
      Bằng chứng: mục Ghi chú có thông báo lỗi của Modulith và cách bạn sửa.
- [ ] **7. ADR của bạn.** Viết `docs/adr/0002-*.md` theo `docs/adr/template.md`, ví dụ "Maven hay Gradle" hoặc
      "Java 21 hay 25".
      Bằng chứng: Claude review nội dung ADR (có ít nhất hai lựa chọn và đánh đổi thật), không chỉ xem file có tồn tại.
- [ ] **8. CI.** Đẩy repo lên GitHub, xem workflow `CI` chạy xanh. Cố ý làm một test fail, xem CI đỏ, rồi sửa.
      Bằng chứng: link repo; lịch sử CI có một lần đỏ và lần xanh sau đó.

## Thí nghiệm cổng

Xóa sạch database và dựng lại bằng một chuỗi lệnh:

```bash
docker compose down -v
docker compose up -d
cd backend && ./mvnw spring-boot:run
```

Kết quả mong đợi: schema được Flyway tạo lại y hệt, không cần thao tác tay nào.
Bằng chứng: Claude chạy lại chuỗi lệnh này khi kiểm tra.

## Câu hỏi phải trả lời được

Claude hỏi vấn đáp trong chat. Trả lời bằng lời của bạn; câu trả lời chép từ tài liệu hoặc từ AI sẽ bị hỏi đào sâu thêm.

1. Spring Boot tự cấu hình `DataSource` thế nào, dựa vào điều kiện gì?
2. Vì sao migration đã chạy thì không được sửa?
3. Vì sao `ddl-auto=validate` chứ không phải `update`?
4. `spring.jpa.open-in-view=false` tránh được vấn đề gì?
5. Testcontainers khác gì so với dùng H2 cho test?

## Ghi chú của bạn

- Học được gì:
- Sai ở đâu, sửa thế nào:
- Câu hỏi còn mở:

## Nộp bài

Làm xong thì nhắn **"kiểm tra phase 0"**. Claude kiểm tra bằng chứng, chạy thí nghiệm cổng và hỏi vấn đáp.
Chỉ khi đạt hết mới sang Phase 1; kết quả ghi vào `docs/learning/progress.md`.
