package me.projects.Usermanagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "User Details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    @Column(unique = true,nullable = false,updatable =false)
    private String UserEmail;
    @Column(nullable = false)
    private LocalDateTime lastOnline;
    @Column(name = "Roles",nullable = false)
    private String role;
    private String providerSubject;
    @Column(name = "Deleted")
    private Boolean isDeleted;
}