package co.istad.visal.ecommerce.features.order;

import co.istad.visal.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.visal.ecommerce.features.order.dto.OrderResponse;
import co.istad.visal.ecommerce.features.order.dto.UpdateOrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse createNew(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createNew(jwt.getSubject(), createOrderRequest);
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/me")
    public List<OrderResponse> findMyOrders(@AuthenticationPrincipal Jwt jwt) {
        return orderService.findMyOrders(jwt.getSubject());
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable UUID id) {
        return orderService.findById(id);
    }

    @PutMapping("/{id}")
    public OrderResponse updateById(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateOrderRequest updateOrderRequest) {
        return orderService.updateById(id, updateOrderRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        orderService.deleteById(id);
    }
}
