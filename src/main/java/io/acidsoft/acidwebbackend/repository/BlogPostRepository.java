package io.acidsoft.acidwebbackend.repository;

import io.acidsoft.acidwebbackend.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    Optional<BlogPost> findBySlug(String slug);
    List<BlogPost> findByIsPublishedTrueOrderByCreatedAtDesc();
    Boolean existsBySlug(String slug);
}
