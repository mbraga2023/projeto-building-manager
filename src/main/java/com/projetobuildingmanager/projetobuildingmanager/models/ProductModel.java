package com.projetobuildingmanager.projetobuildingmanager.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductModel extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String contact;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    public static Long getSerialversionuid() {
        return serialVersionUID;
    }

    @Lob
    private String nomeimg;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    @CreationTimestamp // Automatically set on creation
    @DateTimeFormat(pattern = "MM:HH dd/MM/yyyy") // Corrected pattern
    private LocalDateTime createdAt;

    @UpdateTimestamp // Automatically updated on every update
    @DateTimeFormat(pattern = "MM:HH dd/MM/yyyy") // Corrected pattern
    private LocalDateTime updatedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNomeimg() {
        return nomeimg;
    }

    public void setNomeimg(String nomeimg) {
        this.nomeimg = nomeimg;
    }

  

}
