package co.istad.visal.ecommerce.service;

import co.istad.visal.ecommerce.dto.CategoryResponse;
import co.istad.visal.ecommerce.dto.CreateCategoryRequest;
import co.istad.visal.ecommerce.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    /**
     * កែប្រែ category
     * @param Id
     * @param updateCategoryRequest ព័ត៌មាន category ថ្មី
     * @return CategoryResponse
     */

    CategoryResponse upadteById(Integer id, UpdateCategoryRequest updateCategoryRequest);


    /**
     * បង្កើត category ថ្មី
     * @param createCategoryRequest ព័ត៌មានសម្រាប់បង្កើត category ថ្មី
     * @return CategoryResponse
     */
     CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);

    /**
     * ទាញព័ត៌មាន category ទាំងអស់ by paginations
     * @return សំណុំនៃ CategoryResponse
     */

    Page<CategoryResponse> findAll(Pageable pageable);

}
