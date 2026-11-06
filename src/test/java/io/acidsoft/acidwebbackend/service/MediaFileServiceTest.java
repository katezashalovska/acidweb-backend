package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.entity.MediaFile;
import io.acidsoft.acidwebbackend.repository.MediaFileRepository;
import io.acidsoft.acidwebbackend.repository.PortfolioCaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MediaFileServiceTest {

    @Mock
    private MediaFileRepository mediaFileRepository;

    @Mock
    private PortfolioCaseRepository portfolioCaseRepository;

    @InjectMocks
    private MediaFileService mediaFileService;

    @Captor
    private ArgumentCaptor<MediaFile> mediaCaptor;

    @Test
    void storesVerifiedPngUsingDetectedContentType() {
        byte[] png = new byte[]{
                (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A, 0x00
        };
        MockMultipartFile file = new MockMultipartFile("file", "project.png", "image/png", png);
        when(mediaFileRepository.save(any(MediaFile.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var result = mediaFileService.uploadImage(file);

        org.mockito.Mockito.verify(mediaFileRepository).save(mediaCaptor.capture());
        MediaFile saved = mediaCaptor.getValue();
        assertThat(result.contentType()).isEqualTo("image/png");
        assertThat(result.url()).endsWith("/" + saved.getId());
        assertThat(saved.getContent()).isEqualTo(png);
        assertThat(saved.getOriginalFilename()).isEqualTo("project.png");
    }

    @Test
    void rejectsFilesThatAreNotSupportedImages() {
        MockMultipartFile file = new MockMultipartFile("file", "payload.txt", "text/plain", "not an image".getBytes());

        assertThatThrownBy(() -> mediaFileService.uploadImage(file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Only PNG, JPEG, and WebP images are allowed");
    }
}
