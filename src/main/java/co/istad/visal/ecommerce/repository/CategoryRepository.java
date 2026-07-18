package co.istad.visal.ecommerce.repository;

import co.istad.visal.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Auto generated queries
    // SELECT * FROM categories WHERE name = :name;
    // Derived Query Method - DQM
    Optional<Category> findByName(String name);
}
