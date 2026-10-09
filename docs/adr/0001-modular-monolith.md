# ADR 0001: Bắt đầu bằng modular monolith (Spring Modulith)

- Trạng thái: Đã chấp nhận
- Ngày: 2026-10-09

## Bối cảnh

Mirs Film có khoảng 12 module nghiệp vụ (phim, rạp, suất chiếu, đặt vé, thanh toán, loyalty, ...).
Một người làm toàn bộ, mục tiêu chính là học. Phase 7 sẽ tách một service để học hệ phân tán.

## Quyết định

Một ứng dụng Spring Boot duy nhất, chia package theo module nghiệp vụ ngay dưới `com.mirsfilm`
(ví dụ `com.mirsfilm.catalog`, `com.mirsfilm.booking`). Spring Modulith kiểm tra ranh giới:

- Chỉ package gốc của module là API công khai; package con (ví dụ `catalog.internal`) là nội bộ.
- `ModularityTests` làm build fail khi module này dùng nội bộ của module khác.
- Module nói chuyện với nhau qua API công khai hoặc qua sự kiện (từ Phase 4).

## Hệ quả

- Một lần deploy, một database, transaction cục bộ: đơn giản cho Phase 0–6.
- Ranh giới được kiểm tra tự động, nên khi tách service ở Phase 7, đường cắt đã rõ sẵn.
- Phải giữ kỷ luật: không chia sẻ entity JPA giữa các module, chỉ tham chiếu bằng id.

## Khi nào xem lại

Phase 7, hoặc sớm hơn nếu một module cần scale hay deploy độc lập.
