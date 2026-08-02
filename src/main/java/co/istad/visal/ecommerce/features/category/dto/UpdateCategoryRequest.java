package co.istad.visal.ecommerce.features.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
        @NotBlank(message = "Name is requires")
        @Size(max = 50)
        String name,

        @Size(max = 500)
        String description,

        @Size(max = 255)
        String icon
) {
}
