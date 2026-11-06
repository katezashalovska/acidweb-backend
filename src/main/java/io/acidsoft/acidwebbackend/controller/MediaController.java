package io.acidsoft.acidwebbackend.controller;

import io.acidsoft.acidwebbackend.dto.common.ApiResponse;
import io.acidsoft.acidwebbackend.dto.response.MediaFileResponse;
import io.acidsoft.acidwebbackend.entity.MediaFile;
import io.acidsoft.acidwebbackend.service.MediaFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MediaController {

    private final MediaFileService mediaFileService;

    @GetMapping("/media/{id}")
    public ResponseEntity<byte[]> getMedia(@PathVariable UUID id) {
        MediaFile mediaFile = mediaFileService.getRequiredMediaFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaFile.getContentType()))
                .cacheControl(CacheControl.maxAge(365, TimeUnit.DAYS).cachePublic())
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .body(mediaFile.getContent());
    }

    @PostMapping(value = "/admin/media/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<MediaFileResponse>> uploadImage(@RequestParam("file") MultipartFile file) {
        MediaFileResponse mediaFile = mediaFileService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Image uploaded", mediaFile));
    }

    @DeleteMapping("/admin/media/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteMedia(@PathVariable UUID id) {
        mediaFileService.deleteUploadedMedia(id);
        return ResponseEntity.ok(ApiResponse.success("Image deleted", null));
    }
}
