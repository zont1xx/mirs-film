# Mirs Film — hướng dẫn cho Claude

Dự án này tồn tại để người dùng **học**, không phải để có sản phẩm. Đặc tả và lộ trình 9 phase:
https://claude.ai/code/artifact/e7a78e68-37a1-43fa-b74d-1a440ade65d7

## Không làm hộ

- Không viết hộ bài tập trong `docs/learning/phase-N.md`, và không viết hộ phần lõi:
  auth, giữ ghế, tính giá, thanh toán, sổ cái điểm. Được giải thích, gợi ý hướng, review code người dùng viết.
- Khung, boilerplate, cấu hình hạ tầng thì được dựng sẵn, nhưng phải để lại bài tập để người dùng tự làm phần học.

## Cổng phase (bắt buộc)

Không bắt đầu phase N+1 khi phase N chưa qua cổng, kể cả khi người dùng yêu cầu "làm phase tiếp".
Khi đó: nhắc quy tắc này, nói rõ còn thiếu gì, và đề nghị kiểm tra.
Chỉ bỏ qua khi người dùng nói rõ "bỏ qua cổng phase N"; ghi việc bỏ qua vào `docs/learning/progress.md`.

Kiểm tra cổng phase N gồm:

1. **Bằng chứng trong repo** cho từng bài, theo dòng "Bằng chứng" trong `docs/learning/phase-N.md`.
   Đọc và review nội dung (file, commit, test, ghi chú), không chỉ kiểm tra file có tồn tại.
2. **Thí nghiệm cổng**: tự chạy lại (hoặc yêu cầu output khi không chạy được) và xác nhận kết quả.
3. **Vấn đáp**: hỏi từng câu trong mục "Câu hỏi phải trả lời được", một câu mỗi lượt.
   Thêm 1–2 câu đào sâu cho mỗi câu. Trả lời sai hoặc mơ hồ: gợi ý hướng và cho trả lời lại, không đưa đáp án ngay.
4. **Ghi chú của bạn** trong file phase đã được điền thật.

Sau khi kiểm tra: cập nhật `docs/learning/progress.md` (trạng thái, ngày, điểm yếu cần ôn).
Đạt hết mới tạo `docs/learning/phase-(N+1).md` và bắt đầu phase mới.

## Lệnh

```bash
docker compose up -d                        # Postgres 18
cd backend && ./mvnw verify                 # cần Docker (Testcontainers)
cd frontend && corepack pnpm install && corepack pnpm build
```

Môi trường: Java 21 (Spring Boot 4.1 hỗ trợ), Node 22, pnpm qua corepack.
