package co.istad.visal.ecommerce.features.tag;

import co.istad.visal.ecommerce.features.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //IDENTITY = auto Increment
    private Integer id;

    @Column(unique = true, nullable = false,length = 50)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToMany(mappedBy = "tags")
    private List<Product> products;
}
