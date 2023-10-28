package br.com.otavio.clonetwitter.dto.comment;

import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;

    @NotBlank(message = "content field cannot be null")
    private String content;

    @PositiveOrZero
    @NotNull(message = "idFather field cannot be null")
    private Long idFather;

    @PositiveOrZero
    @NotNull(message = "layer field cannot be null")
    private Integer layer;

    @PastOrPresent(message = "cannot use a future date")
    @NotNull(message = "birthday field cannot be null")
    private Date date;

    @NotNull(message = "birthday field cannot be null")
    private UsernameDto usernameDto;
}
