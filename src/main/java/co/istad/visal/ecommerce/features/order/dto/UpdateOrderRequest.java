package co.istad.visal.ecommerce.features.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record UpdateOrderRequest(
        @NotBlank(message = "Address is required")
        @Size(max = 255)
        String address,

        @PositiveOrZero(message = "Discount cannot be negative")
        Float discount,

        @Size(max = 255)
        String remark,

        Boolean status
) {
}
