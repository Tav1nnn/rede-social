package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.LikeRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class LikeService {

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private LikeRepository likeRepository;

    public void createNewLike(Long id) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setCreate_at(new Date(new java.util.Date().getTime()));
        likeEntity.setUser(DozerMapper.parseObject(userService.getUser(), UserEntity.class));
        likeEntity.setPublication(DozerMapper.parseObject(publicationService.findById(id), PublicationEntity.class));

        likeRepository.save(likeEntity);
    }

    public void deleteLike(Long id) {
        LikeEntity entity = likeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        likeRepository.delete(entity);
    }
}
