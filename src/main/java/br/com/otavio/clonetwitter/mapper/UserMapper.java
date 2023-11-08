package br.com.otavio.clonetwitter.mapper;

import br.com.otavio.clonetwitter.dto.comment.CommentDto;
import br.com.otavio.clonetwitter.dto.comment.CommentListDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.CommentEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "key", target = "id")
    UserEntity toUserEntity(UserDto dto);
}
