package org.nightfury.repository;

import org.nightfury.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    Post findBySlug(String slug);

    List<Post> findByUserId(UUID userId);
}
