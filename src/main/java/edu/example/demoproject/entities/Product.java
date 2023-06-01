package edu.example.demoproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(length = 128, name = "name")
    private String name;

    @Column(length = 128, name = "image")
    private String image;

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

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Item> items;
}