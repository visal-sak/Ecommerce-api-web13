package co.istad.visal.ecommerce.features.tag.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTagRequest(
        @NotBlank(message = "Name is requires")
        @Size(max = 50)
        String name
) {
}
