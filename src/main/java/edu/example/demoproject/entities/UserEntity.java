package edu.example.demoproject.entities;


import lombok.*;
import javax.persistence.*;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Entity
@Table(name="user_entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 128, name="user_id")
    private Long id;

    @Column(length = 128, name="full_name")
    private String fullName;

    @Column(length = 128, name="account_name")
    private String accountName;

    @Column(length = 128)
    private String email;

    @Column(length = 128)
    private String password;

    @Column(name="enabled")
    private boolean enabled;

}
