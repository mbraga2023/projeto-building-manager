package com.projetobuildingmanager.projetobuildingmanager.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid Email")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Invalid Password")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "Invalid name")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Invalid Unit number")
    private String unitNumber;

    @Column(nullable = true)
    private String garageSpot;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String observations;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    @CreationTimestamp // Automatically set on creation
    private LocalDateTime createdAt;

    @UpdateTimestamp // Automatically updated on every update
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
    joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns =  @JoinColumn(name = "roles_id"))
    private List<Roles> roles;

}
