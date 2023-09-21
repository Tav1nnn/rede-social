package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.dto.publication.NewPublicationDto;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserService userService;

    public void createNewPublication(NewPublicationDto publicationDto){
        PublicationEntity publicationEntity = createPublicationEntity(publicationDto);
        UserEntity userEntity = getUserEntityFromService();
        publicationEntity.setUser(userEntity);

        publicationRepository.save(publicationEntity);
    }

    private PublicationEntity createPublicationEntity(NewPublicationDto publicationDto) {
        PublicationEntity publicationEntity = new PublicationEntity();
        publicationEntity.setCaption(publicationDto.caption());
        publicationEntity.setCreate_at(new Date(new java.util.Date().getTime()));
        return publicationEntity;
    }

    private UserEntity getUserEntityFromService() {
        return DozerMapper.parseObject(userService.getUser(), UserEntity.class);
    }
}
