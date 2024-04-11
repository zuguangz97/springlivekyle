package net.nvsoftware.ProductService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="PRODUCT_NAME")
    private String name;
    private long price;
    private long quantity;
    @Builder
    public static ProductEntity build(String name, long price, long quantity) {
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        ProductEntity entity = new ProductEntity();
        entity.name = name;
        entity.price = price;
        entity.quantity = quantity;
        return entity;
    }
}