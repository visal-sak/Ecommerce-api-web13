package co.istad.visal.ecommerce.features.product.dto;

import co.istad.visal.ecommerce.features.tag.dto.TagResponse;
import co.istad.visal.ecommerce.features.category.dto.CategoryResponse;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductResponse(
        String code,
        String slug,
        String name,
        Integer qty,
        BigDecimal unitPrice,
        String thumbnail,
        String description,
        Boolean isAvailable,
        List<TagResponse> tags,
        CategoryResponse category
        ) {
}
