package com.social_app.social.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    
    private Long id;
    @NotEmpty
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @NotNull
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Password must be between 6 and 16 characters")
    @Size(min = 6, max = 16)
    private String password;
    private String about;
}
