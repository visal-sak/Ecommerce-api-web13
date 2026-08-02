package co.istad.visal.ecommerce.features.tag;

import co.istad.visal.ecommerce.features.tag.dto.CreateTagRequest;
import co.istad.visal.ecommerce.features.tag.dto.TagResponse;
import co.istad.visal.ecommerce.features.tag.dto.UpdateTagRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {

    void toEntity(UpdateTagRequest updateTagRequest, @MappingTarget Tag tag);

    Tag mapTagRequestToTag(CreateTagRequest createTagRequest);

    TagResponse mapTagToTagResponse(Tag tag);
}
