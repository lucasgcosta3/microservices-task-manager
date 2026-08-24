package com.lucas.userservice.service;

import com.lucas.userservice.dto.request.UserRequest;
import com.lucas.userservice.dto.response.UserResponse;
import com.lucas.userservice.entity.User;
import com.lucas.userservice.exception.UserNotFoundException;
import com.lucas.userservice.mapper.UserMapper;
import com.lucas.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request) {
        var user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var userSaved = userRepository.save(user);

        return userMapper.toUserResponse(userSaved);
    }

    public List<UserResponse> findAll() {
        var users = userRepository.findAll();
        return userMapper.toUserResponseList(users);
    }

    public UserResponse findById(Long id) {
        var user = findUserById(id);
        return userMapper.toUserResponse(user);
    }

    public UserResponse update(Long id, UserRequest request) {
        var user = findUserById(id);

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

        var userSaved = userRepository.save(user);
        return userMapper.toUserResponse(userSaved);
    }

    public void delete(Long id) {
        var user = findUserById(id);
        userRepository.delete(user);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}
