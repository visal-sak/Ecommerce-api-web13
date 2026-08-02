package co.istad.visal.ecommerce.features.media;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media, UUID> {

    Page<Media> findByIsDraft(Pageable pageable, Boolean isDraft);

    Optional<Media> findByName(String name);
}
