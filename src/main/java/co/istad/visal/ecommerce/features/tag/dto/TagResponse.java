package co.istad.visal.ecommerce.features.tag.dto;

import lombok.Builder;

@Builder
public record TagResponse(
        Integer id,
        String name,
        Boolean isDeleted
) {
}
