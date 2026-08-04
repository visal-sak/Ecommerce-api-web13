package co.istad.visal.ecommerce.features.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    // Auto generated queries
    // SELECT * FROM categories WHERE name = :name;
    // Derived Query Method - DQM
    Optional<Category> findByName(String name);

    // SELECT EXISTS(SELECT * FROM categories WHERE name = :name);
    boolean existsByName(String name);

    // SELECT * FROM categories WHERE name ILIKE %:keyword% OR description ILIKE %:keyword%;
    List<Category> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String name, String description);

}
