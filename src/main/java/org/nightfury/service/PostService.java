package org.nightfury.service;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.nightfury.entity.Post;
import org.nightfury.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> getPostsByUserId(UUID userId) {
        return postRepository.findByUserId(userId);
    }

    public Post getPostBySlug(String slug) {
        return postRepository.findBySlug(slug);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(UUID id, Post updatedPost) {
        Post post = getPostById(id);
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        post.setSlug(updatedPost.getSlug());
        post.setUser(updatedPost.getUser());
        return postRepository.save(post);
    }

    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }
}
