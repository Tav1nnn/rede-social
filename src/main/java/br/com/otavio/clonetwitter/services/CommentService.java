package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.dto.comment.CommentInsertDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.CommentEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.CommentRepository;
import br.com.otavio.clonetwitter.repositories.PublicationRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserService userService;

    public void newComment(CommentInsertDto commentInsertDto) {
        System.out.println("teste");
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

    public CommentEntity findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
    }
}


