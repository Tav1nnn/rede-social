package br.com.otavio.clonetwitter.dto.user;

import br.com.otavio.clonetwitter.dto.publication.PublicationDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.*;
import org.apache.catalina.User;
import org.dozer.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
