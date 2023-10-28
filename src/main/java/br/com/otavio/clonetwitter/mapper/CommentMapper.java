package br.com.otavio.clonetwitter.mapper;

import br.com.otavio.clonetwitter.dto.comment.CommentDto;
import br.com.otavio.clonetwitter.dto.comment.CommentListDto;
import br.com.otavio.clonetwitter.dto.publication.*;
import br.com.otavio.clonetwitter.entities.CommentEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "id", target = "key")
    CommentListDto toCommentiListDto(CommentEntity entity);
    @Mapping(source = "id", target = "key")
    CommentDto toCommentDto(CommentEntity entity);

}
