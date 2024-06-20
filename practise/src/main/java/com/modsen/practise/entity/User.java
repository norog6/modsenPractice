package com.modsen.practise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "login",unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name="user_roles")
    @Column(name = "roles")
    private Set<Role> roles;

    @OneToMany(mappedBy = "orders")
    private List<Order> orders;
}