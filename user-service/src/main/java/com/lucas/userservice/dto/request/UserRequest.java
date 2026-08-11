package com.lucas.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "The field 'name' is required")
        String name,

        @NotBlank(message = "The field 'email' is required")
        @Email(message = "The email must be valid")
        String email,

        @NotBlank(message = "The field 'password' is required")
        @Size(min = 6, message = "The password must be at least 6 characters long")
        String password

){
}
