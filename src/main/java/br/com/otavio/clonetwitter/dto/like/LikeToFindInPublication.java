package br.com.otavio.clonetwitter.dto.like;

import jakarta.validation.constraints.NotBlank;

public record LikeToFindInPublication(
        @NotBlank(message = "idFather field cannot be null")
        String username
) {
}
