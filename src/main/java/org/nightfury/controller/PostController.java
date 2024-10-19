package org.nightfury.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.nightfury.entity.Post;
import org.nightfury.entity.User;
import org.nightfury.service.PostService;
import org.nightfury.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        List<User> users = userService.getAllUsers();
        model.addAttribute("posts", posts);
        model.addAttribute("users", users);
        return "posts";
    }

    @GetMapping("/id/{id}")
    public String getPostById(@PathVariable UUID id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/slug/{slug}")
    public String getPostBySlug(@PathVariable String slug, Model model) {
        Post post = postService.getPostBySlug(slug);
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("post") Post post,
        @RequestParam("userId") UUID userId) {
        User user = userService.getUserById(userId);
        post.setUser(user);
        post.generateSlug();
        postService.createPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable UUID id, Model model) {
        try {
            Post post = postService.getPostById(id);
            model.addAttribute("post", post);
            return "edit-post";
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable UUID id,
        @Valid @ModelAttribute("post") Post post) {
        Post originalPost = postService.getPostById(id);
        post.setUser(originalPost.getUser());
        post.setSlug(originalPost.getSlug());
        postService.updatePost(id, post);
        return "redirect:/posts";
    }


    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable UUID id) {
        try {
            postService.deletePost(id);
            return "redirect:/posts";
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }
}
