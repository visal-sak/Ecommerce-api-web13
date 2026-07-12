package co.istad.visal.ecommerce.service;

import co.istad.visal.ecommerce.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    /**
     * ទាញព័ត៌មាន category ទាំងអស់
     * @return សំណុំនៃ CategoryResponse
     */
    List<CategoryResponse> findAll();

}
