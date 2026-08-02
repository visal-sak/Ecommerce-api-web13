package co.istad.visal.ecommerce.features.media;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    // update draft by name
    // update isDarft to true
    void draftByName(String name);

    //delete media by name
    void deleteByname(String name);
    //find by pageable
    Page<MediaResponse> findAll(int pageNumber , int pageSize);

    // find by name
    MediaResponse findByName(String name);
    // multiple upload
    List<MediaResponse> upload(List<MultipartFile> files);
    // single upload
    MediaResponse upload(MultipartFile file);
}
