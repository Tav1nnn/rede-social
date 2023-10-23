package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.dto.comment.CommentListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationCommentDto extends PublicationDto{
    private List<CommentListDto> listCommentDto;
}
