package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.dto.comment.CommentListDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"key", "caption", "create_at", "user", "listCommentDto"})
public class PublicationCommentDto extends PublicationDto{

    @NotNull(message = "listComment field cannot be null")
    private List<CommentListDto> listCommentDto;
}
