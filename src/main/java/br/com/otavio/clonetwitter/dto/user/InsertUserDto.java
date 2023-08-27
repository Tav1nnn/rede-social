package br.com.otavio.clonetwitter.dto.user;

import br.com.otavio.clonetwitter.exceptions.validation.user.InsertUserValid;
import jakarta.validation.constraints.NotBlank;

@InsertUserValid
public class InsertUserDto extends UserDto{
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "password field is required")
    private String password;

    public InsertUserDto() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
