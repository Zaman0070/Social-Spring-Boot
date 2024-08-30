package com.social_app.social.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "posts")
public class PostModel {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1000) 
    private String content;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    private CategoryModel category;
    @ManyToOne
    private UserModel user;
    
}
