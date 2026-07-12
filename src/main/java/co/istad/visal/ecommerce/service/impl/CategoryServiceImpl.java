package co.istad.visal.ecommerce.service.impl;

import co.istad.visal.ecommerce.dto.CategoryResponse;
import co.istad.visal.ecommerce.entity.Category;
import co.istad.visal.ecommerce.repository.CategoryRepository;
import co.istad.visal.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findAll() {
        // ទាញយកទិន្នន័យចេញពី database
        List<Category> categories = categoryRepository.findAll();

        // បំលែង entity ទៅជា DTO
        return categories.stream().map(
                category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .icon(category.getIcon())
                        .description(category.getDescription())
                        .build()
        ).toList();
    }
}
