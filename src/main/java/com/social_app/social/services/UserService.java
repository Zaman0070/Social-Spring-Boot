package com.social_app.social.services;

import java.util.List;

import com.social_app.social.payloads.UserDto;

public interface UserService {

    UserDto registerNewUser(UserDto user);

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Long id);

    UserDto getUser(Long id);

    List<UserDto> getAllUser();

    void deleteUser(Long id);

}
