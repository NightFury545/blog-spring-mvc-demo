package org.nightfury.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank(message = "Title is required")
    @Size(max = 24, message = "Title must not exceed 24 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(max = 7240, message = "Content must not exceed 7240 characters")
    private String content;

    @NotBlank(message = "Slug is required")
    @Size(max = 24, message = "Slug must not exceed 24 characters")
    private String slug;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        generateSlug();
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        generateSlug();
    }

    public Post() {
    }

    public void generateSlug() {
        this.slug = title.toLowerCase()
            .replaceAll("[^a-z0-9]+", "-")
            .replaceAll("^-|-$", "");
    }
}
