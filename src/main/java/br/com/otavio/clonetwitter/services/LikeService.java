package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.dto.publication.PublicationDto;
import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.mapper.PublicationMapper;
import br.com.otavio.clonetwitter.repositories.LikeRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import br.com.otavio.clonetwitter.services.exceptions.UserAlreadyLikedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private UserService userService;

    @Autowired
    @Lazy
    private PublicationService publicationService;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PublicationMapper publicationMapper;

    public void createNewLike(Long id) {
        UserEntity userEntity = getUserEntity();
        PublicationEntity publicationEntity = findPublicationById(id);

        if (hasUserAlreadyLiked(userEntity, publicationEntity)) {
            throw new UserAlreadyLikedException("You already liked this post");
        }

        LikeEntity likeEntity = createLikeEntity(userEntity, publicationEntity);

        likeRepository.save(likeEntity);
    }

    public void deleteLike(Long id) {
        LikeEntity entity = likeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        likeRepository.delete(entity);
    }

    public Long countLike(PublicationDto publicationDto) {
        PublicationEntity entity = publicationMapper.toPublicationEntity(publicationDto);
        return likeRepository.countByPublication(entity);
    }

    private UserEntity getUserEntity() {
        return DozerMapper.parseObject(userService.getUser(), UserEntity.class);
    }

    private PublicationEntity findPublicationById(Long id) {
        return DozerMapper.parseObject(publicationService.findById(id), PublicationEntity.class);
    }

    private boolean hasUserAlreadyLiked(UserEntity userEntity, PublicationEntity publicationEntity) {
        return likeRepository.findByUserAndPublication(userEntity, publicationEntity).isPresent();
    }

    private LikeEntity createLikeEntity(UserEntity userEntity, PublicationEntity publicationEntity) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setCreate_at(new Date(new java.util.Date().getTime()));
        likeEntity.setUser(userEntity);
        likeEntity.setPublication(publicationEntity);
        return likeEntity;
    }

}
