package co.istad.visal.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //IDENTITY = auto Increment
    private Integer id;

    @Column(unique = true, nullable = false,length = 50)
    private String name;

    private String icon;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category category;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
