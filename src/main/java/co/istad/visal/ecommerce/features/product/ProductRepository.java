package co.istad.visal.ecommerce.features.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends
        JpaRepository<Product, Integer> {

    // SELECT * FROM products WHERE name ILIKE %:keyword% OR code ILIKE %:keyword% OR slug ILIKE %:keyword%;
    Page<Product> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseOrSlugContainingIgnoreCase(
            String name, String code, String slug, Pageable pageable);

}
