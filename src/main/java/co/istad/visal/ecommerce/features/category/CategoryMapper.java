package co.istad.visal.ecommerce.features.category;

import co.istad.visal.ecommerce.features.category.dto.UpdateCategoryRequest;
import co.istad.visal.ecommerce.features.category.dto.CategoryResponse;
import co.istad.visal.ecommerce.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    void toEntity(UpdateCategoryRequest updateCategoryRequest,@MappingTarget Category category);

    // what is source ? => Parameter
    // what is target? => Return
    Category mapCategoryRequesttoCategory(CreateCategoryRequest createCategoryRequest);


    @Mapping(source = "name", target = "cateName")
    CategoryResponse mapCategoryToCategoryResponse(Category category);
}
