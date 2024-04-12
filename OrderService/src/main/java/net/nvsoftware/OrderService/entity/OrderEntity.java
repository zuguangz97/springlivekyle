package net.nvsoftware.OrderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long productId;
    private long quantity;
    private long totalAmount;
    private Instant orderDate;
    private String orderStatus;
    public static class OrderEntityBuilder {
        public OrderEntity build() {
            Objects.requireNonNull(orderDate, "Order time cannot be null");
            Objects.requireNonNull(orderStatus, "Order status cannot be null");

            return new OrderEntity(id, productId, quantity, totalAmount, orderDate, orderStatus);
        }
    }
}