package br.com.otavio.clonetwitter.dto.comment;

import br.com.otavio.clonetwitter.dto.user.UsernameDto;
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
public class CommentListDto extends CommentDto{

    private List<CommentListDto> commentListDtos = new ArrayList<>();
}
