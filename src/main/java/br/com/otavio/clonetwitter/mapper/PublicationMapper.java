package br.com.otavio.clonetwitter.mapper;

import br.com.otavio.clonetwitter.dto.publication.*;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    PublicationDto toPublicationDto(PublicationEntity entity);
    PublicationLikeDto toPublicationLikeDto(PublicationEntity entity);
    PublicationShareDto toPublicationShareDto(PublicationEntity entity);
    PublicationCommentDto toPublicationCommentDto(PublicationEntity entity);
    PublicationInteractionsNumberDTO toPublicationInteractionsNumberDto(PublicationEntity entity);
}
