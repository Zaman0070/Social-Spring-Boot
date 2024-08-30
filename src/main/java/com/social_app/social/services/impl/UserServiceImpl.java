package com.social_app.social.services.impl;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.social_app.social.models.UserModel;
import com.social_app.social.payloads.UserDto;
import com.social_app.social.repository.UserRepo;
import com.social_app.social.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
        UserModel newUser = dtoToUser(user);
        UserModel savedUser = userRepo.save(newUser);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, Long id) {
        UserModel userFind = userRepo.findById(id).orElseThrow(
             () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found!")
        );
        userFind.setName(user.getName());
        userFind.setEmail(user.getEmail());
        userFind.setPassword(user.getPassword());
        userFind.setAbout(user.getAbout());
        UserModel updateUser = userRepo.save(userFind);
        UserDto userDto = userToDto(updateUser);
        return userDto;
        
    }

    @Override
    public UserDto getUser(Long id) {
        UserModel userFind = userRepo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found!")
       );
       return userToDto(userFind);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserModel> users = userRepo.findAll();
        return users.stream().map(user -> userToDto(user)).toList();
    }

    @Override
    public void deleteUser(Long id) {
        UserModel userFind = userRepo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found!")
       );
       userRepo.delete(userFind);
    }


    public UserModel dtoToUser(UserDto userDto){
        UserModel user = modelMapper.map(userDto, UserModel.class);

        
       
        return user;
    }

    public UserDto userToDto(UserModel user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }


    

}
