package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.dto.comment.CommentInsertDto;
import br.com.otavio.clonetwitter.dto.comment.CommentListDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import br.com.otavio.clonetwitter.entities.CommentEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.CommentRepository;
import br.com.otavio.clonetwitter.repositories.PublicationRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserService userService;

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

        System.out.println(userDto.getKey());

        UserEntity userEntity = DozerMapper.parseObject(userDto, UserEntity.class);

        System.out.println(userEntity.getUsername());

        commentEntity.setUserEntity(userEntity);

        commentEntity.setDate(new Date(new java.util.Date().getTime()));

        commentRepository.save(commentEntity);
    }

    public CommentEntity findById(Long id) {
        System.out.println("teste");

        CommentEntity entity= commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));

        entity.setUserEntity(entity.getUserEntity());

        System.out.println(entity.getUserEntity().getUsername());
        System.out.println("teste");
        return entity;
    }

    public List<CommentListDto> teste(Long id) {
        PublicationEntity publicationEntity = publicationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("id not found")
        );

        //lista de comentarios
        List<CommentEntity> commentEntityList = commentRepository.findByPublicationEntity(publicationEntity);

        List<CommentListDto> commentDtoList = new ArrayList<>();

        for(CommentEntity entity : commentEntityList){
            CommentListDto dto = new CommentListDto();
            dto.setId(entity.getId());
            dto.setLayer(entity.getLayer());
            dto.setDate(entity.getDate());
            dto.setContent(entity.getContent());
            dto.setIdFather(entity.getIdFather());
            UsernameDto userDto = new UsernameDto(entity.getUserEntity().getUsername());
            dto.setUsernameDto(userDto);
            commentDtoList.add(dto);
        }

        Map<Long, CommentListDto> commentMap = new HashMap<>();
        List<CommentListDto> commentArborList = new ArrayList<>();

        for (CommentListDto dto : commentDtoList) {
            commentMap.put(dto.getId(), dto);

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

}


