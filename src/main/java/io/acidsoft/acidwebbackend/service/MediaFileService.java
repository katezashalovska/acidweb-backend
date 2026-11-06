package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.dto.response.MediaFileResponse;
import io.acidsoft.acidwebbackend.entity.MediaFile;
import io.acidsoft.acidwebbackend.exception.ResourceNotFoundException;
import io.acidsoft.acidwebbackend.repository.MediaFileRepository;
import io.acidsoft.acidwebbackend.repository.PortfolioCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaFileService {

    public static final long MAX_IMAGE_BYTES = 5L * 1024 * 1024;

    private final MediaFileRepository mediaFileRepository;
    private final PortfolioCaseRepository portfolioCaseRepository;

    @Transactional
    public MediaFileResponse uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("An image file is required");
        }
        if (file.getSize() > MAX_IMAGE_BYTES) {
            throw new IllegalArgumentException("Image must not exceed 5 MB");
        }

        byte[] content;
        try {
            content = file.getBytes();
        } catch (IOException exception) {
            throw new IllegalArgumentException("Could not read the uploaded image");
        }

        String contentType = detectImageContentType(content);
        MediaFile mediaFile = MediaFile.builder()
                .id(UUID.randomUUID())
                .content(content)
                .contentType(contentType)
                .originalFilename(safeFilename(file.getOriginalFilename()))
                .sizeBytes(content.length)
                .build();

        return toResponse(mediaFileRepository.save(mediaFile));
    }

    @Transactional(readOnly = true)
    public MediaFile getRequiredMediaFile(UUID id) {
        return mediaFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MediaFile", "id", id));
    }

    @Transactional(readOnly = true)
    public void requireMediaFile(UUID id) {
        if (id != null && !mediaFileRepository.existsById(id)) {
            throw new ResourceNotFoundException("MediaFile", "id", id);
        }
    }

    @Transactional
    public void deleteIfUnused(UUID id) {
        if (id != null && !portfolioCaseRepository.existsByImageId(id)) {
            mediaFileRepository.deleteById(id);
        }
    }

    @Transactional
    public void deleteUploadedMedia(UUID id) {
        if (portfolioCaseRepository.existsByImageId(id)) {
            throw new IllegalStateException("Remove or replace the image from its portfolio case before deleting it");
        }
        if (!mediaFileRepository.existsById(id)) {
            throw new ResourceNotFoundException("MediaFile", "id", id);
        }
        mediaFileRepository.deleteById(id);
    }

    private MediaFileResponse toResponse(MediaFile mediaFile) {
        return new MediaFileResponse(
                mediaFile.getId(),
                "/api/v1/media/" + mediaFile.getId(),
                mediaFile.getContentType(),
                mediaFile.getOriginalFilename(),
                mediaFile.getSizeBytes()
        );
    }

    private String safeFilename(String filename) {
        if (filename == null || filename.isBlank()) {
            return "image";
        }
        String normalized = filename.replace('\\', '/');
        String basename = normalized.substring(normalized.lastIndexOf('/') + 1);
        return basename.length() > 255 ? basename.substring(0, 255) : basename;
    }

    private String detectImageContentType(byte[] content) {
        if (content.length >= 8 && Arrays.equals(Arrays.copyOf(content, 8), new byte[]{
                (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A
        })) {
            return "image/png";
        }
        if (content.length >= 3 && content[0] == (byte) 0xFF && content[1] == (byte) 0xD8 && content[2] == (byte) 0xFF) {
            return "image/jpeg";
        }
        if (content.length >= 12
                && content[0] == 'R' && content[1] == 'I' && content[2] == 'F' && content[3] == 'F'
                && content[8] == 'W' && content[9] == 'E' && content[10] == 'B' && content[11] == 'P') {
            return "image/webp";
        }
        throw new IllegalArgumentException("Only PNG, JPEG, and WebP images are allowed");
    }
}
