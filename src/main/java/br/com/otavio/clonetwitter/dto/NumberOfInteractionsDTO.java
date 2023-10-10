package br.com.otavio.clonetwitter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NumberOfInteractionsDTO(
        Long like,
        Long share,
        Long comment
) {
}
