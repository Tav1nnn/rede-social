package br.com.otavio.clonetwitter.mapper;

import br.com.otavio.clonetwitter.dto.publication.*;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    @Mapping(source = "id", target = "key")
    PublicationDto toPublicationDto(PublicationEntity entity);
    @Mapping(source = "id", target = "key")
    PublicationLikeDto toPublicationLikeDto(PublicationEntity entity);
    @Mapping(source = "id", target = "key")
    PublicationShareDto toPublicationShareDto(PublicationEntity entity);
    @Mapping(source = "id", target = "key")
    PublicationCommentDto toPublicationCommentDto(PublicationEntity entity);
    @Mapping(source = "id", target = "key")
    PublicationInteractionsNumberDTO toPublicationInteractionsNumberDto(PublicationEntity entity);
}
