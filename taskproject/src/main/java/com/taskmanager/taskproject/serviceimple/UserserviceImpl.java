package com.taskmanager.taskproject.serviceimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanager.taskproject.entity.Users;
import com.taskmanager.taskproject.payload.UserDto;
import com.taskmanager.taskproject.repository.UserRepository;
import com.taskmanager.taskproject.service.UserService;

@Service
public class UserserviceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
    	userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users users = userDtoToEntity(userDto);
        Users savedUser = userRepository.save(users);
        return entityToUserDto(savedUser);
    }

    private Users userDtoToEntity(UserDto userDto) {
        Users users = new Users();
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());  // Corrected method name
        return users;
    }

    private UserDto entityToUserDto(Users savedUser) {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPassword(savedUser.getPassword());
        userDto.setName(savedUser.getName());
        return userDto;
    }
}
