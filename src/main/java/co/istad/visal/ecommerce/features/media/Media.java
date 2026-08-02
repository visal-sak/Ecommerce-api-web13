package co.istad.visal.ecommerce.features.media;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medias")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    private String extension;

    @Column(nullable = false)
    private Float size;

    @Column(nullable = false, length = 32)
    private String mediaType;

    @Column(nullable = false)
    private Boolean isDraft;
}
