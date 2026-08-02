package co.istad.visal.ecommerce.features.product;


import co.istad.visal.ecommerce.features.category.CategoryMapper;
import co.istad.visal.ecommerce.features.product.dto.CreateProductRequest;
import co.istad.visal.ecommerce.features.product.dto.PatchProductRequest;
import co.istad.visal.ecommerce.features.product.dto.ProductResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(PatchProductRequest dto, @MappingTarget Product entity);

    ProductResponse toproductResponse(Product product);

    Product toEntity(CreateProductRequest createProductRequest);
}
