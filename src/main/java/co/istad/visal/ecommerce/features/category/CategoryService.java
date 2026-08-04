package co.istad.visal.ecommerce.features.category;

import co.istad.visal.ecommerce.features.category.dto.UpdateCategoryRequest;
import co.istad.visal.ecommerce.features.category.dto.CategoryResponse;
import co.istad.visal.ecommerce.features.category.dto.CreateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryResponse findById(Integer id);


    void deleteById(Integer id);

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

    /**
     * ស្វែងរក category តាម keyword (name ឬ description)
     * @param keyword ពាក្យគន្លឹះសម្រាប់ស្វែងរក
     * @return សំណុំនៃ CategoryResponse ដែលត្រូវនឹង keyword
     */
    List<CategoryResponse> search(String keyword);

}
