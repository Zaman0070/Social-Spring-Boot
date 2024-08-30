package com.social_app.social.payloads;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class PostDto {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
    private CategoryDto category;
    private UserDto user;
   
    
}
