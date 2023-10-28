package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationShareDto extends PublicationDto{
    @JsonProperty("shares")
    private List<UsernameDto> usernameOfLikeList = new ArrayList<>();
}
