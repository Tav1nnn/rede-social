package br.com.otavio.clonetwitter.dto.publication;

import jakarta.validation.constraints.NotBlank;

public record NewPublicationDto(
        @NotBlank(message = "username field cannot be null")
        String caption
) {
}
