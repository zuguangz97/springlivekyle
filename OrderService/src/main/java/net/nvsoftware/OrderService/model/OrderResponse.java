package net.nvsoftware.OrderService.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private Instant orderDate;
    private long totalAmount;
    private String orderStatus;
    private ProductResponse productResponse;
    private PaymentResponse paymentResponse;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductResponse {
        private long id;
        private String name;
        private long price;
        private long quantity;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentResponse {
        private long id;
        private long orderId;
        private String paymentStatus;
        private String paymentMode;
        private Instant paymentDate;
        private long totalAmount;
    }
}
