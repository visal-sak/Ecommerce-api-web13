package co.istad.visal.ecommerce.features.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends
        JpaRepository<Product, Integer> {
}
