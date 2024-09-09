package com.social_app.social.payloads;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



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
    private Set<CommentDto> comments = new HashSet<>();
   
    
}
