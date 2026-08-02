package co.istad.visal.ecommerce.features.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record PatchProductRequest(
        String name,
        @Min(0)
        Integer qty,
        @Min(0)
        BigDecimal unitPrice,
        String description,
        Boolean isAvailable,
        @Positive
        Integer categoryId,
        // null, [null, null]
        List<@NotNull(message = "Tag ID cannot be null") Integer> tagIds
) {
}
