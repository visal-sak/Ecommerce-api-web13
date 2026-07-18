package co.istad.visal.ecommerce.mapper;

import co.istad.visal.ecommerce.dto.CategoryResponse;
import co.istad.visal.ecommerce.dto.CreateCategoryRequest;
import co.istad.visal.ecommerce.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // what is source ? => Parameter
    // what is target? => Return
    Category mapCategoryRequesttoCategory(CreateCategoryRequest createCategoryRequest);


    @Mapping(source = "name", target = "cateName")
    CategoryResponse mapCategoryToCategoryResponse(Category category);
}
