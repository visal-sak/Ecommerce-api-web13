package co.istad.visal.ecommerce.features.tag;

import co.istad.visal.ecommerce.features.tag.dto.CreateTagRequest;
import co.istad.visal.ecommerce.features.tag.dto.TagResponse;
import co.istad.visal.ecommerce.features.tag.dto.UpdateTagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagResponse findById(Integer id) {
        return tagRepository.findById(id)
                .map(tagMapper::mapTagToTagResponse)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tag has not been found"
                        )
                );
    }

    @Override
    public void deleteById(Integer id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tag has not been found"
                        )
                );
        tagRepository.delete(tag);
    }

    @Override
    public TagResponse updateById(Integer id, UpdateTagRequest updateTagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tag has not been found"
                        )
                );

        if (tagRepository.existsByName(updateTagRequest.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Tag already exists");
        }

        tagMapper.toEntity(updateTagRequest, tag);
        tag = tagRepository.save(tag);

        return tagMapper.mapTagToTagResponse(tag);
    }

    @Override
    public TagResponse createNew(CreateTagRequest createTagRequest) {
        if (tagRepository.existsByName(createTagRequest.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Tag name is already exits"
            );
        }

        Tag newTag = tagMapper.mapTagRequestToTag(createTagRequest);
        newTag.setIsDeleted(false);

        newTag = tagRepository.save(newTag);

        return tagMapper.mapTagToTagResponse(newTag);
    }

    @Override
    public Page<TagResponse> findAll(Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);
        return tags.map(tagMapper::mapTagToTagResponse);
    }
}
