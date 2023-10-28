package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.ShareEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    private String caption;
    private Date create_at;
    private UsernameDto user;

}
