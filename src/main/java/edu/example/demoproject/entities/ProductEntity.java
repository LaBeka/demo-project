package edu.example.demoproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Table(name="product_entities")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(length = 128, name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "initial_price")
    private Double initialPrice;

    @Column(name = "storage_qty")
    private Integer storageQty;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "current_price")
    private Double currentPrice;

    @Column(name = "new_product")
    private boolean newProduct;

    @Column(name = "brand_name")
    private String brand;

    @Column(name = "category_name")
    private String category;

}