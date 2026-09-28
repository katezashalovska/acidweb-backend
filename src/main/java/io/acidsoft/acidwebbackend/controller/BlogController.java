package io.acidsoft.acidwebbackend.controller;

import io.acidsoft.acidwebbackend.dto.common.ApiResponse;
import io.acidsoft.acidwebbackend.dto.request.BlogPostRequest;
import io.acidsoft.acidwebbackend.entity.BlogPost;
import io.acidsoft.acidwebbackend.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    // Public endpoints
    @GetMapping("/blog")
    public ResponseEntity<ApiResponse<List<BlogPost>>> getPublishedPosts() {
        List<BlogPost> posts = blogService.getAllPublishedPosts();
        return ResponseEntity.ok(ApiResponse.success(posts));
    }

    @GetMapping("/blog/{slug}")
    public ResponseEntity<ApiResponse<BlogPost>> getPostBySlug(@PathVariable String slug) {
        BlogPost post = blogService.getPostBySlug(slug);
        return ResponseEntity.ok(ApiResponse.success(post));
    }

    // Admin protected endpoints
    @GetMapping("/admin/blog")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<BlogPost>>> getAllPostsForAdmin() {
        List<BlogPost> posts = blogService.getAllPostsForAdmin();
        return ResponseEntity.ok(ApiResponse.success(posts));
    }

    @PostMapping("/admin/blog")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BlogPost>> createPost(@Valid @RequestBody BlogPostRequest request) {
        BlogPost created = blogService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Blog post created", created));
    }

    @PutMapping("/admin/blog/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BlogPost>> updatePost(@PathVariable Long id, @Valid @RequestBody BlogPostRequest request) {
        BlogPost updated = blogService.updatePost(id, request);
        return ResponseEntity.ok(ApiResponse.success("Blog post updated", updated));
    }

    @DeleteMapping("/admin/blog/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id) {
        blogService.deletePost(id);
        return ResponseEntity.ok(ApiResponse.success("Blog post deleted", null));
    }
}
