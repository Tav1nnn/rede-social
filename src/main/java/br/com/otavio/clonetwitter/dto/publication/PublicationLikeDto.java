package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"key", "caption", "create_at", "user", "likes"})
public class PublicationLikeDto extends PublicationDto{
    @JsonProperty("likes")
    @NotNull(message = "usernameOfLikeList field cannot be null")
    private List<UsernameDto> usernameOfLikeList = new ArrayList<>();
}
