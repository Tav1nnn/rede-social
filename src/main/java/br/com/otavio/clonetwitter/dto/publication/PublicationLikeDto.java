package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationLikeDto extends PublicationDto{
    @JsonProperty("likes")
    private List<UsernameDto> usernameOfLikeList;
}
