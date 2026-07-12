package co.istad.visal.ecommerce.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String icon,
        String description
) {
}
