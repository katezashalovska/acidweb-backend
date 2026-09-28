package io.acidsoft.acidwebbackend.service;

import io.acidsoft.acidwebbackend.dto.request.BlogPostRequest;
import io.acidsoft.acidwebbackend.entity.BlogPost;
import io.acidsoft.acidwebbackend.exception.ResourceNotFoundException;
import io.acidsoft.acidwebbackend.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogPostRepository blogPostRepository;

    @Transactional(readOnly = true)
    public List<BlogPost> getAllPublishedPosts() {
        return blogPostRepository.findByIsPublishedTrueOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public BlogPost getPostBySlug(String slug) {
        return blogPostRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost", "slug", slug));
    }

    @Transactional(readOnly = true)
    public List<BlogPost> getAllPostsForAdmin() {
        return blogPostRepository.findAll();
    }

    @Transactional
    public BlogPost createPost(BlogPostRequest request) {
        if (blogPostRepository.existsBySlug(request.getSlug())) {
            throw new IllegalArgumentException("Blog post slug already exists: " + request.getSlug());
        }

        BlogPost post = BlogPost.builder()
                .slug(request.getSlug())
                .title(request.getTitle())
                .category(request.getCategory())
                .summary(request.getSummary())
                .content(request.getContent())
                .author(request.getAuthor())
                .publishedDate(request.getPublishedDate())
                .isPublished(request.getIsPublished() != null ? request.getIsPublished() : true)
                .build();

        return blogPostRepository.save(post);
    }

    @Transactional
    public BlogPost updatePost(Long id, BlogPostRequest request) {
        BlogPost existingPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost", "id", id));

        existingPost.setTitle(request.getTitle());
        existingPost.setCategory(request.getCategory());
        existingPost.setSummary(request.getSummary());
        existingPost.setContent(request.getContent());
        existingPost.setAuthor(request.getAuthor());
        existingPost.setPublishedDate(request.getPublishedDate());
        if (request.getIsPublished() != null) {
            existingPost.setIsPublished(request.getIsPublished());
        }

        return blogPostRepository.save(existingPost);
    }

    @Transactional
    public void deletePost(Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new ResourceNotFoundException("BlogPost", "id", id);
        }
        blogPostRepository.deleteById(id);
    }
}
