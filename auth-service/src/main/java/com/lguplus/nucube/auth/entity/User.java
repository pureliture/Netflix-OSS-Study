package com.lguplus.nucube.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public List<Role> getRolesList() {
        return Arrays.stream(roles.split(","))
                .map(Role::valueOf)
                .collect(Collectors.toList());
    }
}
