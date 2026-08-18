package co.istad.visal.ecommerce.features.order;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    // SELECT * FROM orders WHERE customer_id = :customerId;
    List<Order> findByCustomerId(String customerId, Sort sort);

}
