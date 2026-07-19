package co.istad.visal.ecommerce.mapper;

import co.istad.visal.ecommerce.dto.CategoryResponse;
import co.istad.visal.ecommerce.dto.CreateCategoryRequest;
import co.istad.visal.ecommerce.dto.UpdateCategoryRequest;
import co.istad.visal.ecommerce.entity.Category;
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
