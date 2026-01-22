package com.example.authservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data //gives getter,setter,custom args constructor,toString(),hashcode() methods
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique=true)
    private String email;
    @Column(nullable=false)
    private String password;
}