package br.com.otavio.clonetwitter.dto.comment;

import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String content;
    private Long idFather;
    private Integer layer;
    private Date date;
    private UsernameDto usernameDto;
}
