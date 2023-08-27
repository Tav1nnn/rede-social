package br.com.otavio.clonetwitter.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.catalina.User;

import java.io.Serializable;
import java.sql.Date;


public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    @NotBlank(message = "username field cannot be null")
    private String username;

    @NotBlank(message = "email field cannot be null")
    @Email(message = "this field needs to be an email")
    private String email;
    @NotBlank(message = "cep field cannot be null")
    @Size(min = 8, max = 8, message = "this field needs to be a CEP")
    private String cep;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotBlank(message = "biography field cannot be null")
    private String biography;

    public UserDto(){

    }

    public UserDto(Long id, String username, String email, String cep, Date birthday, String biography) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.cep = cep;
        this.birthday = birthday;
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
