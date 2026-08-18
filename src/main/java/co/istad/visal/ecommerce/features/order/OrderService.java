package co.istad.visal.ecommerce.features.order;

import co.istad.visal.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.visal.ecommerce.features.order.dto.OrderResponse;
import co.istad.visal.ecommerce.features.order.dto.UpdateOrderRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    /**
     * Create a new order for the authenticated customer.
     * @param customerId identifier of the authenticated customer (JWT subject)
     * @param createOrderRequest order information
     * @return OrderResponse
     */
    OrderResponse createNew(String customerId, CreateOrderRequest createOrderRequest);

    /**
     * Get all orders.
     * @return list of OrderResponse
     */
    List<OrderResponse> findAll();

    /**
     * Get all orders that belong to the authenticated customer.
     * @param customerId identifier of the authenticated customer (JWT subject)
     * @return list of OrderResponse
     */
    List<OrderResponse> findMyOrders(String customerId);

    /**
     * Get an order by its ID.
     * @param id order ID
     * @return OrderResponse
     */
    OrderResponse findById(UUID id);

    /**
     * Update an existing order.
     * @param id order ID
     * @param updateOrderRequest new order information
     * @return OrderResponse
     */
    OrderResponse updateById(UUID id, UpdateOrderRequest updateOrderRequest);

    /**
     * Delete an order by its ID.
     * @param id order ID
     */
    void deleteById(UUID id);
}
