package io.acidsoft.acidwebbackend.dto.response;

import java.util.UUID;

public record MediaFileResponse(
        UUID id,
        String url,
        String contentType,
        String originalFilename,
        long sizeBytes
) {
}
