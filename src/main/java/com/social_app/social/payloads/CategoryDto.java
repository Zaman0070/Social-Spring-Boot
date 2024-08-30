package com.social_app.social.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {

    private Long id;
    @NotEmpty
    @Size(min = 6, max = 250, message = "Title must be between 6 and 250 characters")
    private String title;
    private String description;

    
}
