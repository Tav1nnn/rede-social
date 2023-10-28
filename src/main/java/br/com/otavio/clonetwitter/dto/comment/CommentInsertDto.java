package br.com.otavio.clonetwitter.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CommentInsertDto(
        @NotBlank(message = "content field cannot be null")
        String content,
        @PositiveOrZero
        @NotNull(message = "idFather field cannot be null")
        Long id_father,
        @PositiveOrZero
        @NotNull(message = "publicationID field cannot be null")
        Long publicationID
) {
}
