package org.nightfury.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    private UUID id;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 24, message = "Username must be between 3 and 24 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
