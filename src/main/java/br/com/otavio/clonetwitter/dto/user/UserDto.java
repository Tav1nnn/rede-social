package br.com.otavio.clonetwitter.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import org.apache.catalina.User;
import org.dozer.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.sql.Date;


@JsonPropertyOrder({"key", "username", "email", "cep", "birthday", "biography"})
public class UserDto extends RepresentationModel<UserDto> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    @NotBlank(message = "username field cannot be null")
    private String username;

    @NotBlank(message = "email field cannot be null")
    @Email(message = "this field needs to be an email")
    private String email;
    @NotBlank(message = "cep field cannot be null")
    @Size(min = 8, max = 8, message = "this field needs to be a CEP")
    private String cep;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "cannot use a future date")
    @NotNull(message = "birthday field cannot be null")
    private Date birthday;

    @NotBlank(message = "biography field cannot be null")
    private String biography;

    public UserDto(){

    }

    public UserDto(Long key, String username, String email, String cep, Date birthday, String biography) {
        this.key = key;
        this.username = username;
        this.email = email;
        this.cep = cep;
        this.birthday = birthday;
        this.biography = biography;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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
