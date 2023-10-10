package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.dto.NumberOfInteractionsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationInteractionsNumberDTO extends PublicationDto{

    @JsonProperty("InteractionsNumber")
    private NumberOfInteractionsDTO numberOfInteractionsDTO;
}
