package edu.example.demoproject.entities;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;


@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Entity
@Table(name="cart_entities")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;
}
