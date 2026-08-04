package co.istad.visal.ecommerce.features.tag;

import co.istad.visal.ecommerce.features.tag.dto.CreateTagRequest;
import co.istad.visal.ecommerce.features.tag.dto.TagResponse;
import co.istad.visal.ecommerce.features.tag.dto.UpdateTagRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    TagResponse findById(Integer id);

    void deleteById(Integer id);

    TagResponse updateById(Integer id, UpdateTagRequest updateTagRequest);

    TagResponse createNew(CreateTagRequest createTagRequest);

    Page<TagResponse> findAll(Pageable pageable);

    List<TagResponse> search(String keyword);

}
