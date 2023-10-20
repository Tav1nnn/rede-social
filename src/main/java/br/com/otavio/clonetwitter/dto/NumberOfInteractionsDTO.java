package br.com.otavio.clonetwitter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public record NumberOfInteractionsDTO(
        Long like,
        Long share,
        Long comment
) {
}
