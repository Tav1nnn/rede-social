package br.com.otavio.clonetwitter.dto.user;

import jakarta.validation.constraints.NotBlank;

public class AuthUserDto {

    @NotBlank(message = "username field cannot be null")
    private String username;

    @NotBlank(message = "password field cannot be null")
    private String password;

    public AuthUserDto() {

    }

    public AuthUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
