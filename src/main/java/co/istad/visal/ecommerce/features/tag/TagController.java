package co.istad.visal.ecommerce.features.tag;

import co.istad.visal.ecommerce.features.tag.dto.CreateTagRequest;
import co.istad.visal.ecommerce.features.tag.dto.TagResponse;
import co.istad.visal.ecommerce.features.tag.dto.UpdateTagRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/{id}")
    public TagResponse findById(@PathVariable Integer id) {
        return tagService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        tagService.deleteById(id);
    }

    @PutMapping("/{id}")
    public TagResponse updateById(@PathVariable Integer id,
                                  @Valid @RequestBody UpdateTagRequest updateTagRequest) {
        return tagService.updateById(id, updateTagRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TagResponse createNew(@Valid @RequestBody CreateTagRequest createTagRequest) {
        return tagService.createNew(createTagRequest);
    }

    @GetMapping
    public Page<TagResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortById);
        return tagService.findAll(pageable);
    }

}
