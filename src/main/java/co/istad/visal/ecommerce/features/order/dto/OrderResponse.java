package co.istad.visal.ecommerce.features.order.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponse(
        UUID id,
        String customerId,
        String address,
        Float discount,
        Instant orderedAt,
        String remark,
        Boolean status,
        List<OrderLineResponse> orderLines
) {
}
