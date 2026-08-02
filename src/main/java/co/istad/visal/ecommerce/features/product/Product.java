package co.istad.visal.ecommerce.features.product;

import co.istad.visal.ecommerce.features.tag.Tag;
import co.istad.visal.ecommerce.features.category.Category;
import co.istad.visal.ecommerce.features.order.OrderLine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false,length = 100)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private String thumbnail;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines;

    @ManyToMany
    @JoinTable(
            name = "products_tags_data", //change table'name
            joinColumns = @JoinColumn(name = "product_id"), //change product FK
            inverseJoinColumns = @JoinColumn(name = "tag_id")  //change Tag FK
    )
    private List<Tag> tags;
}
