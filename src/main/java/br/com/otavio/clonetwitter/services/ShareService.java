package br.com.otavio.clonetwitter.services;


import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.ShareEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.ShareRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import br.com.otavio.clonetwitter.services.exceptions.UserAlreadyShareException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ShareService {

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private ShareRepository shareRepository;

    public void createNewShare(Long id) {
        UserEntity userEntity = getUserEntity();
        PublicationEntity publicationEntity = findPublicationById(id);

        if (hasUserAlreadyShared(userEntity, publicationEntity)) {
            throw new UserAlreadyShareException("You already shared this post");
        }

        ShareEntity shareEntity = createshareEntity(userEntity, publicationEntity);

        shareRepository.save(shareEntity);
    }

    public void deleteShare(Long id) {
        ShareEntity entity = shareRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        shareRepository.delete(entity);
    }

    private UserEntity getUserEntity() {
        return DozerMapper.parseObject(userService.getUser(), UserEntity.class);
    }

    private PublicationEntity findPublicationById(Long id) {
        return DozerMapper.parseObject(publicationService.findById(id), PublicationEntity.class);
    }

    private boolean hasUserAlreadyShared(UserEntity userEntity, PublicationEntity publicationEntity) {
        return shareRepository.findByUserAndPublication(userEntity, publicationEntity).isPresent();
    }

    private ShareEntity createshareEntity(UserEntity userEntity, PublicationEntity publicationEntity) {
        ShareEntity shareEntity = new ShareEntity();
        shareEntity.setCreate_at(new Date(new java.util.Date().getTime()));
        shareEntity.setUser(userEntity);
        shareEntity.setPublication(publicationEntity);
        return shareEntity;
    }

}
