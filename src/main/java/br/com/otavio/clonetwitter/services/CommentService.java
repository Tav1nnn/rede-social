package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.controllers.CommentController;
import br.com.otavio.clonetwitter.controllers.UserController;
import br.com.otavio.clonetwitter.dto.comment.CommentDto;
import br.com.otavio.clonetwitter.dto.comment.CommentInsertDto;
import br.com.otavio.clonetwitter.dto.comment.CommentListDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import br.com.otavio.clonetwitter.entities.CommentEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.CommentMapper;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.CommentRepository;
import br.com.otavio.clonetwitter.repositories.PublicationRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.Date;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentMapper commentMapper;

    public CommentDto findById(Long id) {
        CommentEntity entity = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("id not found: " + id)
        );
        CommentDto commentDto = commentMapper.toCommentDto(entity);

        commentDto.add(linkTo(methodOn(CommentController.class).findById(id)).withSelfRel());
        return commentDto;
    }

    public void newComment(CommentInsertDto commentInsertDto) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setContent(commentInsertDto.content());
        commentEntity.setIdFather(commentInsertDto.id_father());

        if (commentInsertDto.id_father() != 0) {
            CommentEntity entityFather = commentRepository.findById(commentInsertDto.id_father())
                    .orElseThrow(() -> new ResourceNotFoundException("id not found"));

            commentEntity.setLayer(entityFather.getLayer() + 1);
        } else {
            commentEntity.setLayer(1);
        }

        commentEntity.setPublicationEntity(publicationRepository.findById(commentInsertDto.publicationID())
                .orElseThrow(() -> new ResourceNotFoundException("id not found"))
        );

        UserDto userDto = userService.getUser();

        UserEntity userEntity = DozerMapper.parseObject(userDto, UserEntity.class);

        commentEntity.setUserEntity(userEntity);

        commentEntity.setDate(new Date(new java.util.Date().getTime()));

        commentRepository.save(commentEntity);
    }

    public List<CommentListDto> listComment(Long idPublication) {
        PublicationEntity publicationEntity = publicationRepository.findById(idPublication).orElseThrow(
                () -> new ResourceNotFoundException("id not found")
        );

        //lista de comentarios
        List<CommentEntity> commentEntityList = commentRepository.findByPublicationEntity(publicationEntity);

        List<CommentListDto> commentDtoList = new ArrayList<>();

        //transformar o commentEntityList em CommentDtoList
        for(CommentEntity entity : commentEntityList){
            CommentListDto dto = commentMapper.toCommentiListDto(entity);
            UsernameDto userDto = newUsernameDto(entity.getUserEntity().getUsername(), entity.getUserEntity().getId());
            dto.setUsernameDto(userDto);
            dto.add(linkTo(methodOn(CommentController.class).findById(dto.getKey())).withSelfRel());
            commentDtoList.add(dto);
        }

        Map<Long, CommentListDto> commentMap = new HashMap<>();
        List<CommentListDto> commentArborList = new ArrayList<>();

        for (CommentListDto dto : commentDtoList) {
            commentMap.put(dto.getKey(), dto);

            if (dto.getIdFather() == 0) {
                commentArborList.add(dto);

            } else {
                CommentListDto fatherDto = commentMap.get(dto.getIdFather());

                if (fatherDto != null) {
                    fatherDto.getCommentListDtos().add(dto);
                }
            }
        }

        return commentArborList;
    }

    private UsernameDto newUsernameDto(String username, Long id) {
        UsernameDto usernameDto = new UsernameDto();
        usernameDto.setUsername(username);
        usernameDto.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());

        return usernameDto;
    }
}


