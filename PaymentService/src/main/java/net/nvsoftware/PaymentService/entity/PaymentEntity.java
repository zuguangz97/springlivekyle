package net.nvsoftware.PaymentService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // Corrected import for JPA
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "Payment_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long orderId;
    @NonNull
    private String paymentMode;

    private long totalAmount;

    private Instant paymentDate;
    @NonNull
    private String paymentStatus;

    public static class PaymentEntityBuilder {
        public PaymentEntity build() {
            if (paymentMode == null) {
                throw new IllegalStateException("Payment mode cannot be null");
            }
            if (paymentStatus == null) {
                throw new IllegalStateException("Payment status cannot be null");
            }
            return new PaymentEntity(id, orderId, paymentMode, totalAmount, paymentDate, paymentStatus);
        }
    }
}
