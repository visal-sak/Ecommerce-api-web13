package co.istad.visal.ecommerce.features.media;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    @Override
    public List<MediaResponse> upload(List<MultipartFile> files) {
        return files.stream()
                .map(this::upload)
                .toList();
    }

    private final MediaRepository mediaRepository;
    //    private final MediaResponse mediaResponse;

    @Transactional
    @Override
    public MediaResponse findByName(String name) {
        Media media = mediaRepository.findByName(name)
                .orElseThrow(()-> new
                        ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Media has not been found"));
        return builMediaResponse(media);
    }

    private final static String MB = "MB";

    @Value("${media.location}")
    private String mediaLocation;

    @Value("${media.client-path}")
    private String mediaClientPath;

    @Value("${media.base-uri}")
    private String mediaBaseUri;

    @Transactional
    @Override
    public MediaResponse upload(MultipartFile file) {
        // TODO
        // 1. Create path object (ផ្ទុកទីតាំង file)
        String name = UUID.randomUUID().toString();
        // e.g. Vital.png
        int lastIndexDot = file.getOriginalFilename().lastIndexOf('.');
        String extension = file.getOriginalFilename().substring(lastIndexDot + 1);
        Path path = Paths.get(buildMediaPath( name, extension));
        log.info("Uploading media location: {}", path);

        // 2. Copy file
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Media has been uploaded failed"
            );
        }

        // 3. Save into database table
        Media media = new Media();
        media.setName(name);
        media.setExtension(extension);
        media.setSize((float) file.getSize());
        media.setMediaType(file.getContentType());
        media.setIsDraft(false);
        media = mediaRepository.save(media);

        return builMediaResponse(media);
    }

    private MediaResponse builMediaResponse(Media media){

        // 1MB = 1_000_000B
        Float sizeAsMb = media.getSize() / 1_000_000;

        return MediaResponse.builder()
                .id(media.getId())
                .name(media.getName())
                .extension(media.getExtension())
                .mediaType(media.getMediaType())
                .size(sizeAsMb)
                .measurement(MB)
                .uri(buildMediaUri(media))
                .build();
    }


    private String buildMediaUri(Media media){
        return mediaBaseUri
                +mediaClientPath
                +"/"
                + media.getName()
                +"."
                + media.getExtension();

    }

    @Override
    public Page<MediaResponse> findAll(int pageNumber, int pageSize) {
        Sort sortByIdDesc = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByIdDesc);
        return mediaRepository
                .findByIsDraft(pageable,Boolean.FALSE)
                .map(this::builMediaResponse);
    }

    @Override
    public void draftByName(String name) {
        Media media = mediaRepository.findByName(name)
                .orElseThrow(()-> new
                        ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Media has not been found"));
        media.setIsDraft(Boolean.TRUE);
        mediaRepository.save(media);
    }

    @Transactional
    @Override
    public void deleteByname(String name) {
        Media media = mediaRepository.findByName(name)
                .orElseThrow(()-> new
                        ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Media has not been found"));

        if(!media.getIsDraft()){
            return;
        }

//        return builMediaResponse(media);
        // delete from db
        mediaRepository.delete(media);

        Path path = Paths.get(buildMediaPath(media.getName(), media.getExtension()));
        // delete from file system
        try {
            Files.delete(path);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "something went wrong"
            );
        }

    }

    private String buildMediaPath(String mediaName, String mediaExtension) {
        return mediaLocation + mediaName + "." + mediaExtension;
    }
}
