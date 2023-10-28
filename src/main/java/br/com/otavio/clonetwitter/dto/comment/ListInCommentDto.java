package br.com.otavio.clonetwitter.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListInCommentDto {

    @NotNull(message = "listCommentDto field cannot be null")
    private List<CommentListDto> listCommentDto;
}
