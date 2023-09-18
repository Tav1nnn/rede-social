package br.com.otavio.clonetwitter.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AuthUserDto (
    @NotBlank(message = "username field cannot be null")
    String username,
    @NotBlank(message = "password field cannot be null")
    String password
)
{ }
