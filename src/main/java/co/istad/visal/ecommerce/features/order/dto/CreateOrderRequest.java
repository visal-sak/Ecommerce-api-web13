package co.istad.visal.ecommerce.features.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateOrderRequest(
        @NotBlank(message = "Address is required")
        @Size(max = 255)
        String address,

        @PositiveOrZero(message = "Discount cannot be negative")
        Float discount,

        @Size(max = 255)
        String remark,


        @NotEmpty(message = "At least one order line is required")
        List<@Valid OrderLineRequest> orderLines
) {
}
