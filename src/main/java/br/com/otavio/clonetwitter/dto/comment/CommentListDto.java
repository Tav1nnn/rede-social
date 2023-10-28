package br.com.otavio.clonetwitter.dto.comment;

import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "content", "idFather", "layer", "date", "user",})
public class CommentListDto extends CommentDto{

    @NotNull(message = "commentiListDtos field cannot be null")
    private List<CommentListDto> commentListDtos = new ArrayList<>();
}
