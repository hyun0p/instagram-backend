package com.clonecoding.instagrambackend.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@NoArgsConstructor
@Getter
@Entity
public class User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String email;

    private boolean activated;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name="user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles;

    private User() {}

    @Builder
    public User(String username, String name, String password, String email, Set<Role> roles) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.activated = true;
        this.roles = roles;
    }
}
