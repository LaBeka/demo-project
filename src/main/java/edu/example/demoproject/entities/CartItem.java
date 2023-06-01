package edu.example.demoproject.entities;

import lombok.*;

import javax.persistence.*;


@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Entity
@Table(name="cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="id")
    private Product product;

    @Column
    private int qty;
}
