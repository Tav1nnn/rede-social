package br.com.otavio.clonetwitter.dto.user;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsernameDto extends RepresentationModel<UsernameDto> {
    private String username;
}
