package co.istad.visal.ecommerce.service.impl;

import co.istad.visal.ecommerce.dto.CategoryResponse;
import co.istad.visal.ecommerce.dto.CreateCategoryRequest;
import co.istad.visal.ecommerce.dto.UpdateCategoryRequest;
import co.istad.visal.ecommerce.entity.Category;
import co.istad.visal.ecommerce.mapper.CategoryMapper;
import co.istad.visal.ecommerce.repository.CategoryRepository;
import co.istad.visal.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryResponse upadteById(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        // TODO:
        // Validate category ID
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category has not been found"
                        )
                );

        // Validate category name (prevent duplicated name)
        if (categoryRepository.existsByName(updateCategoryRequest.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category already exists");
        }

        categoryMapper.toEntity(updateCategoryRequest, category);
        category = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createNew(CreateCategoryRequest createCategoryRequest) {
        // TODO:
        // 1. Validate all information form DTO
        // Validate category name (unique)
//        Optional<Category> category = categoryRepository.findByName(createCategoryRequest.name());
        Optional<Category> category = categoryRepository.findByName(createCategoryRequest.name());

        if (category.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name is already exits"
            );
        }

        Category newCategory = categoryMapper.mapCategoryRequesttoCategory(createCategoryRequest);
        newCategory.setIsDeleted(false);

        // Validate parent category id
        if (createCategoryRequest.parentCategoryId() != null) {
            Category parentCategory = categoryRepository
                    .findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Parent Category Not Found")
                    );
            newCategory.setParentCategory(parentCategory);
        }

        newCategory = categoryRepository.save(newCategory);

        return categoryMapper.mapCategoryToCategoryResponse(newCategory);

    }


    @Override
    public Page<CategoryResponse> findAll(Pageable pageable) {
        // ទាញយកទិន្នន័យចេញពី database

        Page<Category> categories = categoryRepository.findAll(pageable);

        // បំលែង entity ទៅជា DTO
//        return categories.stream()
//                .map(categoryMapper::mapCategoryToCategoryResponse)
//                .toList();

        return categories.map(categoryMapper::mapCategoryToCategoryResponse);
    }
}
