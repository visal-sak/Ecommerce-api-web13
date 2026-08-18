package co.istad.visal.ecommerce.features.order;

import co.istad.visal.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.visal.ecommerce.features.order.dto.OrderResponse;
import co.istad.visal.ecommerce.features.order.dto.UpdateOrderRequest;
import co.istad.visal.ecommerce.features.product.Product;
import co.istad.visal.ecommerce.features.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createNew(String customerId, CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setAddress(createOrderRequest.address());
        order.setDiscount(createOrderRequest.discount() != null ? createOrderRequest.discount() : 0f);
        order.setRemark(createOrderRequest.remark());
        order.setOrderedAt(Instant.now());
        order.setStatus(false);
        order.setIsDeleted(false);

        List<OrderLine> orderLines = new ArrayList<>();
        createOrderRequest.orderLines().forEach(lineRequest -> {
            Product product = productRepository
                    .findById(lineRequest.productId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Product ID = " + lineRequest.productId() + " has not been found"
                    ));

            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQty(lineRequest.qty());
            orderLine.setUnitPrice(product.getUnitPrice());
            orderLine.setOrder(order);
            orderLines.add(orderLine);
        });
        order.setOrderLines(orderLines);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderedAt"))
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public List<OrderResponse> findMyOrders(String customerId) {
        return orderRepository.findByCustomerId(customerId, Sort.by(Sort.Direction.DESC, "orderedAt"))
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse findById(UUID id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order has not been found"
                ));
    }

    @Override
    public OrderResponse updateById(UUID id, UpdateOrderRequest updateOrderRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order has not been found"
                ));

        order.setAddress(updateOrderRequest.address());
        order.setDiscount(updateOrderRequest.discount() != null ? updateOrderRequest.discount() : order.getDiscount());
        order.setRemark(updateOrderRequest.remark());
        if (updateOrderRequest.status() != null) {
            order.setStatus(updateOrderRequest.status());
        }

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public void deleteById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order has not been found"
                ));
        orderRepository.delete(order);
    }
}
