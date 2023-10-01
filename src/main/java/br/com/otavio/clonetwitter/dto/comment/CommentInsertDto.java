package br.com.otavio.clonetwitter.dto.comment;

public record CommentInsertDto(
        String content,
        Long id_father,
        Long publicationID
) {
}
