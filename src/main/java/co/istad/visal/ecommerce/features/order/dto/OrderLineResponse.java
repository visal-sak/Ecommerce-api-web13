package co.istad.visal.ecommerce.features.order.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderLineResponse(
        Long id,
        Integer productId,
        String productName,
        Integer qty,
        BigDecimal unitPrice
) {
}
