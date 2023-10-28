package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.ShareEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"key", "caption", "create_at", "user"})
public class PublicationDto extends RepresentationModel<PublicationDto> {


    @JsonProperty("id")
    private Long key;

    @NotBlank(message = "caption field cannot be null")
    private String caption;

    @NotNull(message = "create_at field cannot be null")
    @PastOrPresent(message = "cannot use a future date")
    private Date create_at;

    @NotNull(message = "user field cannot be null")
    private UsernameDto user;

}
