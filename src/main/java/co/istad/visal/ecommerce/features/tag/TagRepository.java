package co.istad.visal.ecommerce.features.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends
        JpaRepository<Tag, Integer> {

    // SELECT * FROM tags WHERE name = :name;
    Optional<Tag> findByName(String name);

    // SELECT EXISTS(SELECT * FROM tags WHERE name = :name);
    boolean existsByName(String name);

}
