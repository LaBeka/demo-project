package edu.example.demoproject.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Table(name="picture_entity")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;

    @Column(length = 128, name = "product_id")
    private Long productId;

    @Column(name="pic_content_type")
    private String imageContentType;

    @Lob
    @Column(name = "picture")
    @JdbcType(VarbinaryJdbcType.class)
    private byte[] image;
}
