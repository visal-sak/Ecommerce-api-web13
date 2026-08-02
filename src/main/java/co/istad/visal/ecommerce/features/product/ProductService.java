package co.istad.visal.ecommerce.features.product;

import co.istad.visal.ecommerce.features.product.dto.CreateProductRequest;
import co.istad.visal.ecommerce.features.product.dto.PatchProductRequest;
import co.istad.visal.ecommerce.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    /**
     * Patch Product Id
     */
    ProductResponse patchById(Integer id, PatchProductRequest patchProductRequest);

    // find all products by pagination
    Page<ProductResponse> findAll(int pageNumber , int pageSize);

    /**
     * Create a new product
     */
    void createNew(CreateProductRequest createProductRequest);

}
