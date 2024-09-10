package com.social_app.social.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username; // username is a email
    private String password;

}
