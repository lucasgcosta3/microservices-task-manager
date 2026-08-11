package com.lucas.userservice.mapper;

import com.lucas.userservice.dto.request.UserRequest;
import com.lucas.userservice.dto.response.UserResponse;
import com.lucas.userservice.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);
}
