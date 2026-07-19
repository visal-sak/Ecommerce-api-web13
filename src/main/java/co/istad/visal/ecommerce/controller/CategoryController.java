package co.istad.visal.ecommerce.controller;

import co.istad.visal.ecommerce.dto.CategoryResponse;
import co.istad.visal.ecommerce.dto.CreateCategoryRequest;
import co.istad.visal.ecommerce.dto.UpdateCategoryRequest;
import co.istad.visal.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PutMapping("/{id}")
    public CategoryResponse updateById(@PathVariable Integer id,
                                       @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return categoryService.upadteById(id, updateCategoryRequest);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return categoryService.createNew(createCategoryRequest);
    }

    @GetMapping
    public Page<CategoryResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortById);
        return categoryService.findAll(pageable);
    }

}
