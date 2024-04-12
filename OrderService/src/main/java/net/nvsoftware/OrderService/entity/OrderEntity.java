package net.nvsoftware.OrderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;
    private long productQuantity;
    private long totalAmount;
    private Instant orderTime;
    private String orderStatus;

    public static class OrderEntityBuilder {
        public OrderEntity build() {
            Objects.requireNonNull(orderTime, "Order time cannot be null");
            Objects.requireNonNull(orderStatus, "Order status cannot be null");

            return new OrderEntity(id, productId, productQuantity, totalAmount, orderTime, orderStatus);
        }
    }
}
